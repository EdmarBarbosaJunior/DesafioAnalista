package com.socin.catchworks.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.socin.catchworks.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;

		var problema = cadastraProblema(status.value(), ex.getMessage(), OffsetDateTime.now());

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var campos = cadastraCampos(ex.getBindingResult().getAllErrors());

		var problema = cadastraProblema(status.value(), "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente",
				OffsetDateTime.now(), campos);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	private Problema cadastraProblema(Integer status, String titulo, OffsetDateTime dataHora) {

		var problema = new Problema();
		problema.setStatus(status);
		problema.setTitulo(titulo);
		problema.setDataHora(dataHora);

		return problema;
	}

	private Problema cadastraProblema(Integer status, String titulo, OffsetDateTime dataHora, List<Problema.Campo> campos) {

		var problema = new Problema();
		problema.setStatus(status);
		problema.setTitulo(titulo);
		problema.setDataHora(dataHora);
		problema.setCampos(campos);

		return problema;
	}
	
	private List<Problema.Campo> cadastraCampos(List<ObjectError> erros){
		
		var campos = new ArrayList<Problema.Campo>();

		for (ObjectError error : erros) {			
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			campos.add(new Problema.Campo(nome, mensagem));
		}
		
		return campos;
	}
}

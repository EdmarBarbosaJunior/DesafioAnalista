package com.socin.catchworks.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.socin.catchworks.domain.model.Programador;
import com.socin.catchworks.domain.repository.ProgramadorRepository;
import com.socin.catchworks.domain.service.CadastraProgramadorService;

@RestController
@RequestMapping("/programadores")
public class ProgramadorController {

	@Autowired
	private ProgramadorRepository programadorRepository;

	@Autowired
	private CadastraProgramadorService cadastraProgramador;

	@GetMapping
	public List<String> listar() throws JsonMappingException, JsonProcessingException {
		// return programadorRepository.findAll();
		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(
				"https://api.github.com/search/users?q=type:user+followers:>3419+repos:>0&sort=followers&order=desc",
				String.class);
		
		return cadastraProgramador.teste(response.getBody());
	}

	@GetMapping("/{programadorId}")
	public ResponseEntity<Programador> buscar(@PathVariable Long programadorId) {
		Optional<Programador> programador = programadorRepository.findById(programadorId);

		if (programador.isPresent()) {
			return ResponseEntity.ok(programador.get());
		}

		return ResponseEntity.notFound().build();
	}
}

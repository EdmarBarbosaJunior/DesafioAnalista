package com.socin.catchworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socin.catchworks.domain.exception.NegocioException;
import com.socin.catchworks.domain.functions.ConverteSenha;
import com.socin.catchworks.domain.model.Usuario;
import com.socin.catchworks.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvar(Usuario usuario) {
		
		Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe um usuario cadastrado com este e-mail");
		}
		
		usuario.setSenha(ConverteSenha.HashSenha(usuario.getSenha()));
		
		return usuarioRepository.save(usuario);			
	}
	
	public void excluir(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}
	
}

package com.socin.catchworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socin.catchworks.domain.model.Usuario;
import com.socin.catchworks.domain.repository.UsuarioRepository;
import com.socin.catchworks.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@GetMapping
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long usuarioId){
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		
		if(usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
				
		return ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	public Usuario adicionar(@Valid @RequestBody Usuario usuario) {
		return cadastroUsuario.salvar(usuario);
	}
	
	@PutMapping("/{usuarioID}")
	public ResponseEntity<Usuario> atualizar(@Valid @PathVariable Long usuarioID, @RequestBody Usuario usuario){
		
		if(!usuarioRepository.existsById(usuarioID)) {
			return ResponseEntity.notFound().build();
		}
		
		usuario.setId(usuarioID);
		usuario = cadastroUsuario.salvar(usuario);
		
		return ResponseEntity.ok(usuario);		
	}
	
	@DeleteMapping("/{usuarioID}")
	public ResponseEntity<Void> remover(@PathVariable Long usuarioID){
		
		if(!usuarioRepository.existsById(usuarioID)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroUsuario.excluir(usuarioID);
		
		return ResponseEntity.noContent().build();		
	}
		
}

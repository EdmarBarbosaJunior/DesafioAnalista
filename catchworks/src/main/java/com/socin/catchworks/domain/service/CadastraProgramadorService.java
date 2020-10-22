package com.socin.catchworks.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socin.catchworks.domain.model.Programador;
import com.socin.catchworks.domain.repository.ProgramadorRepository;
@Service
public class CadastraProgramadorService{

	@Autowired
	private ProgramadorRepository programadorRepository;
	
	public List<Programador> salvar(List<Programador> programadores) {
		return programadores.stream().map( programador -> programadorRepository.save(programador)).collect(Collectors.toList());			
	}
	
	public void excluir(Long programadorId) {
		programadorRepository.deleteById(programadorId);
	}
	
	public List<String> teste(String test){
		String campos[] = test.split(",");
		
		List<String> teste2 = new ArrayList<>();
		
		String x = new String();
		for(int i = 0; i< campos.length; i++) {
			if(campos[i].contentEquals("%login%")) {
			}
			teste2.add(campos[i]);
		}
		
		return teste2;
	}
	
}

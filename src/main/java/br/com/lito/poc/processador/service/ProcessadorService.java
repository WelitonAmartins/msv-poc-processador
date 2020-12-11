package br.com.lito.poc.processador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lito.poc.processador.dto.ColetaDto;
import br.com.lito.poc.processador.repository.ProcessadorRepository;

@Service
public class ProcessadorService {
	
	@Autowired
	private ProcessadorRepository repo;
	
	public void salvar(ColetaDto coletaDto){
		repo.salvar(coletaDto);
	}

	public void salvar(List<ColetaDto> lsColetaDto){
		repo.salvar(lsColetaDto);
	}
}

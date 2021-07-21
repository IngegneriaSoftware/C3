package it.unicam.cs.ids.c3.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.c3.backend.entity.Negozio;
import it.unicam.cs.ids.c3.backend.repository.NegozioRepository;

@Service
public class NegozioService {

	private NegozioRepository negozioRepository;

	public NegozioService(NegozioRepository negozioRepository) {
		this.negozioRepository = negozioRepository;
	}
	
	public List<Negozio> findAll(){
		return negozioRepository.findAll();
	}
	
}

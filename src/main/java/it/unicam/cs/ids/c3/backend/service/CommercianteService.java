package it.unicam.cs.ids.c3.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import it.unicam.cs.ids.c3.backend.repository.CommercianteRepository;

@Service
public class CommercianteService {
	
	private CommercianteRepository commercianteRepository;

	public CommercianteService(CommercianteRepository commercianteRepository) {
		this.commercianteRepository = commercianteRepository;
	}

	public List<Commerciante> findAll(){
		return commercianteRepository.findAll();
	}
}

package it.unicam.cs.ids.c3.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.c3.backend.entity.Prodotto;
import it.unicam.cs.ids.c3.backend.repository.ProdottoRepository;

@Service
public class ProdottoService {
	
	private ProdottoRepository prodottoRepository;

	public ProdottoService(ProdottoRepository prodottoRepository) {
		this.prodottoRepository = prodottoRepository;
	}
	
	public List<Prodotto> findAll(){
		return prodottoRepository.findAll();
	}

}

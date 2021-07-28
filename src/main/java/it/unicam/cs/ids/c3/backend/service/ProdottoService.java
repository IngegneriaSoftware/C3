package it.unicam.cs.ids.c3.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.unicam.cs.ids.c3.backend.entity.Prodotto;
import it.unicam.cs.ids.c3.backend.repository.ProdottoRepository;

import javax.annotation.PostConstruct;

@Service
public class ProdottoService {
	
	private DescrizioneProdottoService descrizioneProdottoService;
	private ProdottoRepository prodottoRepository;


	public ProdottoService(DescrizioneProdottoService descrizioneProdottoService, ProdottoRepository prodottoRepository) {
		this.descrizioneProdottoService = descrizioneProdottoService;
		this.prodottoRepository = prodottoRepository;
	}

	public List<Prodotto> findAll(){
		return prodottoRepository.findAll();
	}

	public Prodotto save(Prodotto prodotto){return prodottoRepository.save(prodotto);}

	public Optional<Prodotto> findById(Long id){return prodottoRepository.findById(id);}


}

package it.unicam.cs.ids.c3.backend.service;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.flow.component.notification.Notification;
import it.unicam.cs.ids.c3.backend.entity.*;
import it.unicam.cs.ids.c3.backend.repository.CommercianteRepository;
import it.unicam.cs.ids.c3.backend.repository.DescrizioneProdottoRepository;
import it.unicam.cs.ids.c3.backend.repository.ProdottoRepository;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.c3.backend.repository.NegozioRepository;

import javax.annotation.PostConstruct;

@Service
public class NegozioService {

	private NegozioRepository negozioRepository;
	private CommercianteRepository commerianteRepository;
	private ProdottoRepository prodottoRepository;
	private DescrizioneProdottoRepository descrizioneProdottoRepository;
    private ProdottoService prodottoService ;

	public NegozioService(NegozioRepository negozioRepository, CommercianteRepository commerianteRepository, ProdottoRepository prodottoRepository, DescrizioneProdottoRepository descrizioneProdottoRepository, ProdottoService prodottoService) {
		this.negozioRepository = negozioRepository;
		this.commerianteRepository = commerianteRepository;
		this.prodottoRepository = prodottoRepository;
		this.descrizioneProdottoRepository = descrizioneProdottoRepository;
		this.prodottoService = prodottoService;
	}

	public List<Negozio> findAll(){
		return negozioRepository.findAll();
	}

	public List<Negozio> findAll(String stringFilter) {
		if (stringFilter == null || stringFilter.isEmpty()) {
			return negozioRepository.findAll();
		} else {
			return negozioRepository.search(stringFilter);
		}
	}

	public List<Negozio> findAll(Categoria categoria){
		return negozioRepository.searchByCategoria(categoria);
	}

	public Negozio save(Negozio negozio){return negozioRepository.save(negozio);}


	public  Negozio getById(Long id){ return negozioRepository.getById(id);}

	public void addProduct(Long id, Prodotto prodotto){
		if (id== null ) {
			Notification notification = new Notification("Negozio non trovato", 3000);
					notification.open();
		}


		//negozioRepository.getById(id).getVetrina().add(prodotto);
		this.getById(id).getVetrina().add(prodotto);
		System.out.println("qui");

		}

    public List<Negozio> search(String searchTerm){
		return negozioRepository.search(searchTerm);
	}



/*	@PostConstruct
	public void populateTestData() {
            negozioRepository.deleteAll();
		DescrizioneProdotto descrizioneProdotto = new DescrizioneProdotto("0014","Articolo 4","Articolo 4");
		descrizioneProdottoRepository.save(descrizioneProdotto);
		Commerciante comm1 = new Commerciante("Mario Rossi","86334519757");
		commerianteRepository.save(comm1);
		Prodotto prodotto = new Prodotto(descrizioneProdotto,1);
		prodottoRepository.save(prodotto);
		LinkedList<Prodotto> list = new LinkedList();
		list.add(prodotto);
		Negozio neg1 = new Negozio("Da Rossi","Via Garibaldi 12",list,comm1);
		//prodotto.setNegozio(neg1);
		prodottoService.findById(prodotto.getId()).get().setNegozio(neg1);
		prodottoService.save(prodottoService.findById(prodotto.getId()).get());
		negozioRepository.save(neg1);




		//Commerciante comm2 = new Commerciante("Luigi Bianchi","123456789");
		//Negozio neg2  = new Negozio("Da Bianchi","Via Garibaldi, 13",list,comm2);

	//	commerianteRepository.save(comm2);

	//	negozioRepository.save(neg2);
	}*/
	
}

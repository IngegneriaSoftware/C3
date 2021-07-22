package it.unicam.cs.ids.c3.backend.service;

import java.util.List;

import com.vaadin.flow.component.notification.Notification;
import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import it.unicam.cs.ids.c3.backend.entity.Prodotto;
import it.unicam.cs.ids.c3.backend.repository.CommercianteRepository;
import org.springframework.stereotype.Service;

import it.unicam.cs.ids.c3.backend.entity.Negozio;
import it.unicam.cs.ids.c3.backend.repository.NegozioRepository;

import javax.annotation.PostConstruct;

@Service
public class NegozioService {

	private NegozioRepository negozioRepository;
	private CommercianteRepository commerianteRepository;

	public NegozioService(NegozioRepository negozioRepository, CommercianteRepository commerianteRepository) {
		this.negozioRepository = negozioRepository;
		this.commerianteRepository = commerianteRepository;
	}

	public List<Negozio> findAll(){
		return negozioRepository.findAll();
	}

	public void addProduct(String searchTerm, Prodotto prodotto){
		if (searchTerm== null || searchTerm.isEmpty() ) {
			Notification notification = new Notification("Negozio non trovato", 3000);
					notification.open();
		}


		 Negozio negozio = negozioRepository.search(searchTerm).get(0);
		negozio.getVetrina().add(prodotto);
		negozio.toString();
		if(negozio==null){System.out.println("null");}
		negozioRepository.save(negozio);
		System.out.println("qui");

		}

    public List<Negozio> search(String searchTerm){
		return negozioRepository.search(searchTerm);
	}

	@PostConstruct
	public void populateTestData() {
		if(negozioRepository.count()==0){
		Commerciante comm1 = new Commerciante("Mario Rossi","86334519757");
		Negozio neg1  = new Negozio("Da Rossi","Via Garibaldi, 12",comm1);
		Commerciante comm2 = new Commerciante("Luigi Bianchi","123456789");
		Negozio neg2  = new Negozio("Da Bianchi","Via Garibaldi, 13",comm2);
		commerianteRepository.save(comm1);
		commerianteRepository.save(comm2);
		negozioRepository.save(neg1);
		negozioRepository.save(neg2);}
	}
	
}

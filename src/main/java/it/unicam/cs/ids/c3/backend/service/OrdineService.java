package it.unicam.cs.ids.c3.backend.service;

import it.unicam.cs.ids.c3.backend.entity.Cliente;
import it.unicam.cs.ids.c3.backend.entity.Ordine;
import it.unicam.cs.ids.c3.backend.repository.OrdineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineService {


    private OrdineRepository ordineRepository;

    public OrdineService(OrdineRepository ordineRepository) {
        this.ordineRepository = ordineRepository;
    }

    public List<Ordine> findAll(){return ordineRepository.findAll();}

    public List<Ordine> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return ordineRepository.findAll();
        } else {
            return ordineRepository.searchForCliente(stringFilter);
        }
    }

    public List<Ordine> searchForCliente(String searchTerm){
        return ordineRepository.searchForCliente(searchTerm);
    }

    public Ordine save(Ordine ordine){return ordineRepository.save(ordine);}
}

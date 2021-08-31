package it.unicam.cs.ids.c3.backend.service;

import it.unicam.cs.ids.c3.backend.entity.Cliente;
import it.unicam.cs.ids.c3.backend.entity.Negozio;
import it.unicam.cs.ids.c3.backend.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente save(Cliente cliente){return clienteRepository.save(cliente);}

    public List<Cliente> findAll(){return clienteRepository.findAll();}

    public List<Cliente> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return clienteRepository.findAll();
        } else {
            return clienteRepository.search(stringFilter);
        }
    }

    @PostConstruct
    public void populateTestData() {
        Cliente cliente = new Cliente("Luigino Bianchi","Via Nazionale, 12");
        clienteRepository.save(cliente);
    }

}

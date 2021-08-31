package it.unicam.cs.ids.c3.backend.repository;

import it.unicam.cs.ids.c3.backend.entity.Cliente;
import it.unicam.cs.ids.c3.backend.entity.Negozio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query("select c from Cliente c " +
            "where lower(c.nomeCliente) like lower(concat('%', :searchTerm, '%')) " )
    List<Cliente> search(@Param("searchTerm") String searchTerm);
}

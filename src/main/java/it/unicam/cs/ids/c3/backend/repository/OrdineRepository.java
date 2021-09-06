package it.unicam.cs.ids.c3.backend.repository;

import it.unicam.cs.ids.c3.backend.entity.Cliente;
import it.unicam.cs.ids.c3.backend.entity.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine,Long> {

    @Query("select o from Ordine o " +
            "where lower(o.cliente.nomeCliente) like lower(concat('%', :searchTerm, '%')) " )
    List<Ordine> searchForCliente(@Param("searchTerm") String searchTerm);
}

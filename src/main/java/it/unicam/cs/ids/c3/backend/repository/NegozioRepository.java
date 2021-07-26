package it.unicam.cs.ids.c3.backend.repository;

import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.cs.ids.c3.backend.entity.Negozio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NegozioRepository extends JpaRepository<Negozio,Long>{


    @Query("SELECT c FROM Negozio c WHERE c.commerciante.nomeCommerciante = :searchTerm")
    List<Negozio> search(@Param("searchTerm") String searchTerm);


}

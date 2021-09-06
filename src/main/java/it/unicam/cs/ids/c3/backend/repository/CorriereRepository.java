package it.unicam.cs.ids.c3.backend.repository;

import it.unicam.cs.ids.c3.backend.entity.Commerciante;
import it.unicam.cs.ids.c3.backend.entity.Corriere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CorriereRepository extends JpaRepository<Corriere,Long> {

    @Query("select c from Corriere c " +
            "where lower(c.nomeCorriere) like lower(concat('%', :searchTerm, '%')) " )
    List<Corriere> search(@Param("searchTerm") String searchTerm);
}

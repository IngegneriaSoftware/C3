package it.unicam.cs.ids.c3.backend.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Negozio extends AbstractEntity  {


    private String nomeNegozio;

    private String indirizzo;


    @OneToMany(targetEntity = Prodotto.class, mappedBy = "negozio", cascade = {CascadeType.MERGE} ,fetch = FetchType.EAGER,orphanRemoval=true)
   // @JoinColumn(name = "prodotto_id")
   // @Basic
    private List<Prodotto> vetrina ;





    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="commerciante_id")
    private Commerciante commerciante;

    public Negozio() {
    }

    public Negozio(String nomeNegozio, String indirizzo, List<Prodotto> vetrina, Commerciante commerciante) {
        this.nomeNegozio = nomeNegozio;
        this.indirizzo = indirizzo;
        this.vetrina = vetrina;
        this.commerciante = commerciante;
    }

    public Negozio(String nomeNegozio, String indirizzo, Commerciante commerciante) {
        this.nomeNegozio = nomeNegozio;
        this.indirizzo = indirizzo;
        this.vetrina = new LinkedList<>();
        this.commerciante = commerciante;
    }

    public String getNomeNegozio() {
        return nomeNegozio;
    }

    public void setNomeNegozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<Prodotto> getVetrina() {
        return vetrina;
    }

    public void setVetrina(List<Prodotto> vetrina) {
        this.vetrina = vetrina;
    }

    public Commerciante getCommerciante() {
        return commerciante;
    }

    public void setCommerciante(Commerciante commerciante) {
        this.commerciante = commerciante;
    }
}

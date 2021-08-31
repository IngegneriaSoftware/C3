package it.unicam.cs.ids.c3.backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Ordine extends AbstractEntity implements Serializable {

    private StatusOrdine status;

    @OneToMany(targetEntity = Prodotto.class, mappedBy = "ordine", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Prodotto> prodotti;

    private String puntoRitiro;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    public Ordine() {
    }

    public Ordine(StatusOrdine status, List<Prodotto> prodotti, String puntoRitiro, Cliente cliente) {
        this.status = status;
        this.prodotti = prodotti;
        this.puntoRitiro = puntoRitiro;
        this.cliente = cliente;
    }

    public StatusOrdine getStatus() {
        return status;
    }

    public void setStatus(StatusOrdine status) {
        this.status = status;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public String getPuntoRitiro() {
        return puntoRitiro;
    }

    public void setPuntoRitiro(String puntoRitiro) {
        this.puntoRitiro = puntoRitiro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

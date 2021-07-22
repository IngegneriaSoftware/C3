package it.unicam.cs.ids.c3.backend.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Prodotto extends AbstractEntity implements Cloneable {
	
	  @NotNull
	  @NotEmpty
	  @ManyToOne
	  @JoinColumn(name="descrizioneProdotto_id")
	  private DescrizioneProdotto descrizione;
	  @NotNull
	  @NotEmpty
	  private int quantita;
	 
	  @ManyToOne
	  @JoinColumn(name="negozio_id")
	  private Negozio negozio;

	public Prodotto() {}


	public Prodotto(DescrizioneProdotto descrizione, int quantita) {
		this.descrizione = descrizione;
		this.quantita = quantita;
	}


	public DescrizioneProdotto getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(DescrizioneProdotto descrizione) {
		this.descrizione = descrizione;
	}


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}


	@Override
	public String toString() {
		return "Prodotto{" +
				"descrizione=" + descrizione +
				", quantita=" + quantita +
				", negozio=" + negozio +
				'}';
	}
}

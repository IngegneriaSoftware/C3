package it.unicam.cs.ids.c3.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Prodotto extends AbstractEntity implements Serializable {
	

	 @ManyToOne
	  @JoinColumn(name="descrizioneProdotto_id")
	  private DescrizioneProdotto descrizione;

	  private int quantita;
	 
	  @ManyToOne(targetEntity = Negozio.class)
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

	public String getNomeProdotto(){return descrizione.getNomeProdotto();}

	public void setDescrizione(DescrizioneProdotto descrizione) {
		this.descrizione = descrizione;
	}


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public Negozio getNegozio() {
		return negozio;
	}

	public void setNegozio(Negozio negozio) {
		this.negozio = negozio;
	}
}

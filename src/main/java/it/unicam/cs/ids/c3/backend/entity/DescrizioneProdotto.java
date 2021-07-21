package it.unicam.cs.ids.c3.backend.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class DescrizioneProdotto extends AbstractEntity implements Cloneable{
	
	  @NotNull
	  @NotEmpty
	private String codiceProdotto;
	  @NotNull
	  @NotEmpty
	private String nomeProdotto;
	  @NotNull
	  @NotEmpty
	  private String descrizioneProdotto;
	
	public DescrizioneProdotto() {}

	public DescrizioneProdotto(String codiceProdotto, String nomeProdotto, String descrizioneProdotto) {
		this.codiceProdotto = codiceProdotto;
		this.nomeProdotto = nomeProdotto;
		this.descrizioneProdotto = descrizioneProdotto;
	}

	public String getCodiceProdotto() {
		return codiceProdotto;
	}

	public void setCodiceProdotto(String codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public String getDescrizioneProdotto() {
		return descrizioneProdotto;
	}

	public void setDescrizioneProdotto(String descrizioneProdotto) {
		this.descrizioneProdotto = descrizioneProdotto;
	}
	
	
}

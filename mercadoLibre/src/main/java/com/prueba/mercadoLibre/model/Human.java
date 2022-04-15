package com.prueba.mercadoLibre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Human {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long idHuman;
	
	@Column(name = "adn")
	private String adn;
	
	@Column(name = "isMutant", length = 3)
	private String isMutant;

	public long getIdHuman() {
		return idHuman;
	}

	public void setIdHuman(long idHuman) {
		this.idHuman = idHuman;
	}

	public String getAdn() {
		return adn;
	}

	public void setAdn(String adn) {
		this.adn = adn;
	}

	public String getIsMutant() {
		return isMutant;
	}

	public void setIsMutant(String isMutant) {
		this.isMutant = isMutant;
	}
	
}

package com.prueba.mercadoLibre.dto;

import com.prueba.mercadoLibre.model.Human;

public class HumanDTO {

	private String[] adn;

	public Human humanDTOToHuman(HumanDTO humanDTO) {
		Human human = new Human();
		human.setAdn(String.join(", ", humanDTO.getAdn()));
		return human;
	}

	public String[] getAdn() {
		return adn;
	}

	public void setAdn(String[] adn) {
		this.adn = adn;
	}

	
	
	
}

package com.prueba.mercadoLibre.service;

import com.prueba.mercadoLibre.dto.HumanDTO;
import com.prueba.mercadoLibre.dto.StatsDTO;

public interface IHumanService {

	/**
	 * Servicio encargado de verificar si el humano es mutante
	 * @param humanDto
	 * @return
	 */
	public boolean isMutant(HumanDTO humanDto);
	
	/**
	 * Servicio para calcular estadisiticas
	 * @return
	 */
	public StatsDTO stats();
}

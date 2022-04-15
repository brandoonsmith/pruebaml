package com.prueba.mercadoLibre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prueba.mercadoLibre.controller.HumanController;
import com.prueba.mercadoLibre.dto.HumanDTO;
import com.prueba.mercadoLibre.service.HumanServiceImpl;
import com.prueba.mercadoLibre.service.IHumanService;

@SpringBootTest
class MercadoLibreApplicationTests {

	@Autowired
	HumanController HumanController;
	
	@Autowired
	IHumanService iHumanService;
	
	@Autowired
	HumanServiceImpl humanServiceImpl;
	
	private String[] adnMutant = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","TCCCTA","TCACTG"};
	private String[] adnNoMutant = {"TTGCGA","CAGTGC","TTATGT","AGAAGG","TCCCTA","TCACTG"};
	private String[] adnNoValide = {"TTGCGF","CAGTGC","TUATGT","AGAAGG","TCCCTA","TCACTG"};
	private String[] adnMutantHorizontal = {"ATGCGA","AAAAGC","TTATGT","AGAAGG","TCCCTA","TCACTG"};
	private String[] adnDiagonalInversa = {"ATGCGA","AAAAGC","ATATGT","AGAAGG","TCCCTA","TCACTG"};

	@Test
	public void isMutantRequest() {
		HumanDTO humanDto = new HumanDTO();
		humanDto.setAdn(adnMutant);
		ResponseEntity<String> response = HumanController.isMutant(humanDto);
		assertEquals(response.getStatusCode().toString(),HttpStatus.OK.toString());
	}
	
	@Test
	public void isMutantRequestHorizontal() {
		HumanDTO humanDto = new HumanDTO();
		humanDto.setAdn(adnMutantHorizontal);
		ResponseEntity<String> response = HumanController.isMutant(humanDto);
		assertEquals(response.getStatusCode().toString(),HttpStatus.OK.toString());
	}
	
	@Test
	public void isMutantRequestDiagonalInversa() {
		HumanDTO humanDto = new HumanDTO();
		humanDto.setAdn(adnDiagonalInversa);
		ResponseEntity<String> response = HumanController.isMutant(humanDto);
		assertEquals(response.getStatusCode().toString(),HttpStatus.OK.toString());
	}
	
	@Test
	public void isNotMutantRequest() {
		HumanDTO humanDto = new HumanDTO();
		humanDto.setAdn(adnNoMutant);
		ResponseEntity<String> response = HumanController.isMutant(humanDto);
		assertEquals(response.getStatusCode().toString(),HttpStatus.FORBIDDEN.toString());
	}
	
	@Test
	public void isNotNullStats() {
		assertNotNull(HumanController.stats());
	}
	
	@Test
	public void isMutantServiceValidate() {
		HumanDTO humanDto = new HumanDTO();
		humanDto.setAdn(adnNoValide);
		boolean isMutant = iHumanService.isMutant(humanDto);
		assertTrue(!isMutant);
	}
	

}

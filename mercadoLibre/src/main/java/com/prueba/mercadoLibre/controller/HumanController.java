package com.prueba.mercadoLibre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.mercadoLibre.dto.HumanDTO;
import com.prueba.mercadoLibre.dto.StatsDTO;
import com.prueba.mercadoLibre.service.IHumanService;

@RestController
@RequestMapping
public class HumanController {

	@Autowired
	IHumanService iHumanService;
	
	@PostMapping("/mutant")
	public ResponseEntity<String> isMutant(@RequestBody HumanDTO humanDto) {
		
		if(iHumanService.isMutant(humanDto)) {
			return new ResponseEntity<>(
			          "Is Mutant", 
			          HttpStatus.OK);
		}else {
			return new ResponseEntity<>(
			          "Is not Mutant", 
			          HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/stats")
	public StatsDTO stats() {
		return iHumanService.stats();
		
	}

}

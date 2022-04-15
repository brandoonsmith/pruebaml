package com.prueba.mercadoLibre.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.mercadoLibre.dto.HumanDTO;
import com.prueba.mercadoLibre.dto.StatsDTO;
import com.prueba.mercadoLibre.model.Human;
import com.prueba.mercadoLibre.repository.IHumanRepo;

@Service
public class HumanServiceImpl implements IHumanService {

	private final static String IS_MUTANT = "YES";
	private final static String IS_NOT_MUTANT = "NOT";

	private final Logger logger = Logger.getLogger(HumanServiceImpl.class.getName());

	private char[][] matriz;

	public void setArrayMatrizCuadrada(char[] array, int posicion) {
		this.matriz[posicion] = array;
	}
	
	@Autowired
	IHumanRepo iHumanRepo;

	@SuppressWarnings("finally")
	@Override
	public boolean isMutant(HumanDTO humanDto) {
		try {

			String[] data = humanDto.getAdn();

			matriz = new char[data.length][data.length];

			int longitudMatriz = matriz.length;

			int cadenasEncontradas = 0;

			//Se valida la matriz
			if (!ValidarMatriz(data)) {
				return false;
			}

			//Se recorre cada posición de la matriz 
			//La variable x actuara como una variable en posición Horizontal
			//La variable y actuara como una variable en posición vertical
			do {
				for (int x = 0; x < longitudMatriz; x++) {

					if (cadenasEncontradas < 2) {
						for (int y = 0; y < longitudMatriz; y++) {

							char posicionActual = matriz[x][y];

							// Busqueda Horizontal
							if (cadenasEncontradas < 2) {
								if ((y + 4) <= longitudMatriz) {
									if (BusquedaHorizontal(x, y, posicionActual)) {
										cadenasEncontradas++;

										logger.log(Level.INFO,
												"Encontró cadena de la letra " + posicionActual + " en Busqueda Horizontal, la posición es: " 
												+ x + "," + y);
									}
								}
							} else {
								break;
							}

							if (cadenasEncontradas < 2) {
								if ((x + 4) <= longitudMatriz) {
									if (BusquedaVertical(x, y, posicionActual)) {
										cadenasEncontradas++;

										logger.log(Level.INFO,
												"Encontró cadena de la letra " + posicionActual + " en Busqueda Vertical, la posición es: " 
												+ x + "," + y);
									}
								}
							} else {
								break;
							}

							if (cadenasEncontradas < 2) {
								if ((y + 4) <= longitudMatriz) {
									if ((x + 4) <= longitudMatriz) {
										if (BusquedaDiagonal(x, y, posicionActual)) {
											cadenasEncontradas++;

											logger.log(Level.INFO, "Encontró cadena de la letra " + posicionActual + " en Busqueda Diagonal, la posición es:"
													+ x + "," + y);

										}
									}
								}
							} else {
								break;
							}
							// Busca por Diagonal Inversa
							if (cadenasEncontradas < 2) {
								if (y >= 3) {
									if ((x + 4) <= longitudMatriz) {
										if (BusquedaDiagonalInversa(x, y, posicionActual)) {
											cadenasEncontradas++;

											logger.log(Level.INFO, "Encontró cadena de la letra " + posicionActual
													+ " en DiagonalInversa, la posición es" + x + "," + y);

										}
									}
								}
							} else {
								break;
							}
						}
					} else {
						break;
					}
				}
				break;
				
			//si encuentras más de una secuencia de cuatro letras iguales
			} while (cadenasEncontradas < 2);

			//Si encontro al menos dos cadenas que cumplan la condición, es mutante
			if (cadenasEncontradas == 2) {
				try {
					Human human = humanDto.humanDTOToHuman(humanDto);
					human.setIsMutant(IS_MUTANT);
					iHumanRepo.save(human);
				} catch (Exception ex) {
					logger.log(Level.SEVERE, "Excepcion del sistema:", ex.getMessage());
				} finally {
					return true;
				}

			} else {

				try {
					Human human = humanDto.humanDTOToHuman(humanDto);
					human.setIsMutant(IS_NOT_MUTANT);
					iHumanRepo.save(human);
				} catch (Exception ex) {
					logger.log(Level.SEVERE, "Excepcion del sistema:", ex.getMessage());
				} finally {
					return false;
				}
			}
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Excepcion del sistema:", ex.getMessage());
			return false;
		}
	}

	/**
	 * Se encarga de validar si la matriz contiene letras no permitidas o si hay una
	 * cadena con una mayor longitud que las demas cadenas de la matriz
	 * 
	 * @param data
	 * @return
	 */
	private boolean ValidarMatriz(String[] data) {
		try {
			for (int i = 0; i < data.length; i++) {

				if (!data[i].matches("[ATCGatcg]*")) {
					logger.log(Level.WARNING, "ERROR: Tiene letras fuera del dominio ATCG");
					return false;
				}

				if (data[i].length() != data.length) {
					logger.log(Level.WARNING, "ERROR: una cadena tiene tamaño distinto al de la matriz");
					return false;
				}

				// Conversión a letras mayusculas
				this.setArrayMatrizCuadrada(data[i].toUpperCase().toCharArray(), i);

			}

			return true;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Excepcion del sistema:", ex.getMessage());
			return false;
		}

	}

	private boolean BusquedaHorizontal(int fila, int posicion, char posicionActual) {

		String cadena = "";
		boolean encontroCadena = false;

		cadena = "" + posicionActual + matriz[fila][posicion + 1] + matriz[fila][posicion + 2]
				+ matriz[fila][posicion + 3];

		switch (cadena) {
		case "AAAA":
			encontroCadena = true;
			break;
		case "TTTT":
			encontroCadena = true;
			break;
		case "CCCC":
			encontroCadena = true;
			break;
		case "GGGG":
			encontroCadena = true;
			break;
		default:
			encontroCadena = false;
			break;

		}

		return encontroCadena;
	}

	private boolean BusquedaVertical(int fila, int posicion, char posicionActual) {

		boolean encontroCadena = false;
		String cadena = "";
		cadena = "" + posicionActual + matriz[fila + 1][posicion] + matriz[fila + 2][posicion]
				+ matriz[fila + 3][posicion];

		switch (cadena) {
		case "AAAA":
			encontroCadena = true;
			break;
		case "TTTT":
			encontroCadena = true;
			break;
		case "CCCC":
			encontroCadena = true;
			break;
		case "GGGG":
			encontroCadena = true;
			break;
		default:
			encontroCadena = false;
			break;

		}

		return encontroCadena;
	}

	private boolean BusquedaDiagonal(int fila, int posicion, char posicionActual) {
		String cadena = "";
		cadena = "" + posicionActual + matriz[fila + 1][posicion + 1] + matriz[fila + 2][posicion + 2]
				+ matriz[fila + 3][posicion + 3];
		switch (cadena) {
		case "AAAA":
			return true;
		case "TTTT":
			return true;
		case "CCCC":
			return true;
		case "GGGG":
			return true;
		default:
			return false;

		}
	}

	private boolean BusquedaDiagonalInversa(int fila, int posicion, char posicionActual) {
		String cadena = "";
		cadena = "" + posicionActual + matriz[fila + 1][posicion - 1] + matriz[fila + 2][posicion - 2]
				+ matriz[fila + 3][posicion - 3];
		switch (cadena) {
		case "AAAA":
			return true;
		case "TTTT":
			return true;
		case "CCCC":
			return true;
		case "GGGG":
			return true;
		default:
			return false;

		}
	}

	@Override
	public StatsDTO stats() {
		try {
			StatsDTO stats = new StatsDTO();
			long totalHumanos = iHumanRepo.count();
			logger.log(Level.INFO, "Total Humanos : "+totalHumanos);
			long totalMutants = iHumanRepo.findByisMutant(IS_MUTANT);
			logger.log(Level.INFO, "Total Mutantes : "+totalMutants);
			
			stats.setCount_humnan_dna(totalHumanos);
			stats.setCount_mutant_dna(totalMutants);
			if(totalMutants != 0 && totalHumanos != 0) {
				float ratio = ((float)totalMutants/totalHumanos);
				logger.log(Level.INFO, "Relación : "+ratio);
				stats.setRatio(ratio);
			}
			return stats;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Excepcion del sistema:", ex.getMessage());
			return null;
		}
	}

}

// NOMBRE COMPLETO - RUT - CARRERA
package logica;

import Dominio.Nota;
import java.util.List;

/**
 * Interfaz de estrategia para calcular promedios. Permite cambiar el modo de
 * calculo sin modificar el codigo que la usa.
 */
public interface EstrategiaCalculoPromedio {

	/**
	 * Calcula un promedio usando una lista de notas.
	 *
	 * @param listaNotas lista de notas del estudiante
	 * @return promedio calculado segun la estrategia
	 */
	double calcularPromedio(List<Nota> listaNotas);
}

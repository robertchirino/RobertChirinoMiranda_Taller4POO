// NOMBRE COMPLETO - RUT - CARRERA
package logica;

import Dominio.Nota;
import java.util.List;

/**
 * Estrategia que calcula el promedio general del estudiante. Ignora asignaturas
 * cuyo estado sea "Cursando".
 */
public class EstrategiaPromedioGeneral implements EstrategiaCalculoPromedio {

	/**
	 * Calcula el promedio general de las notas que no esten en estado "Cursando".
	 *
	 * @param listaNotas lista de notas del estudiante
	 * @return promedio general, o 0 si no hay notas validas
	 */
	@Override
	public double calcularPromedio(List<Nota> listaNotas) {
		double suma = 0;
		int contador = 0;

		for (Nota n : listaNotas) {
			if (!n.obtenerEstadoAsignatura().equalsIgnoreCase("Cursando")) {
				suma += n.obtenerCalificacion();
				contador++;
			}
		}

		if (contador == 0) {
			return 0;
		}

		return suma / contador;
	}
}

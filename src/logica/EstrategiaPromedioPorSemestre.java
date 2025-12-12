// NOMBRE COMPLETO - RUT - CARRERA
package logica;

import Dominio.Nota;
import java.util.List;

/**
 * Estrategia que calcula el promedio de un semestre especifico.
 */
public class EstrategiaPromedioPorSemestre implements EstrategiaCalculoPromedio {

	/** Semestre que se desea filtrar para el calculo. */
	private String semestreFiltrado;

	/**
	 * Crea la estrategia indicando el semestre que se desea considerar.
	 *
	 * @param semestreFiltrado semestre a filtrar (ej: "2024-2")
	 */
	public EstrategiaPromedioPorSemestre(String semestreFiltrado) {
		this.semestreFiltrado = semestreFiltrado;
	}

	/**
	 * Calcula el promedio solo con notas del semestre filtrado.
	 *
	 * @param listaNotas lista de notas del estudiante
	 * @return promedio del semestre filtrado, o 0 si no hay notas para ese semestre
	 */
	@Override
	public double calcularPromedio(List<Nota> listaNotas) {
		double suma = 0;
		int contador = 0;

		for (Nota n : listaNotas) {
			if (n.obtenerSemestreCursado().equals(semestreFiltrado)) {
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

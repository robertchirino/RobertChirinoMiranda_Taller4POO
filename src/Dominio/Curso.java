// NOMBRE COMPLETO - RUT - CARRERA
package Dominio;

import java.util.List;

/**
 * Representa un curso/asignatura del sistema. Se identifica por un NRC y
 * contiene datos academicos del curso.
 */
public class Curso {

	/** NRC del curso. */
	private String nrc;
	/** Nombre del curso. */
	private String nombre;
	/** Semestre en el que se dicta el curso. */
	private int semestre;
	/** Creditos del curso. */
	private int creditos;
	/** Area del curso. */
	private String area;
	/** Lista de prerrequisitos. */
	private List<String> listaPrerrequisitos;

	/**
	 * Crea un curso con sus datos.
	 *
	 * @param nrc                 nrc del curso
	 * @param nombre              nombre del curso
	 * @param semestre            semestre del curso
	 * @param creditos            creditos del curso
	 * @param area                area del curso
	 * @param listaPrerrequisitos lista de prerrequisitos
	 */
	public Curso(String nrc, String nombre, int semestre, int creditos, String area, List<String> listaPrerrequisitos) {
		this.nrc = nrc;
		this.nombre = nombre;
		this.semestre = semestre;
		this.creditos = creditos;
		this.area = area;
		this.listaPrerrequisitos = listaPrerrequisitos;
	}

	/** @return nrc del curso */
	public String obtenerNrc() {
		return nrc;
	}

	/** @return nombre del curso */
	public String obtenerNombre() {
		return nombre;
	}

	/** @return semestre del curso */
	public int obtenerSemestre() {
		return semestre;
	}

	/** @return creditos del curso */
	public int obtenerCreditos() {
		return creditos;
	}

	/** @return area del curso */
	public String obtenerArea() {
		return area;
	}

	/** @return lista de prerrequisitos */
	public List<String> obtenerListaPrerrequisitos() {
		return listaPrerrequisitos;
	}
}

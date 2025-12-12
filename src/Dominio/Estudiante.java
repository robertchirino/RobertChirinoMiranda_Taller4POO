// NOMBRE COMPLETO - RUT - CARRERA
package Dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un estudiante del sistema. Un estudiante tambien es un usuario,
 * por lo que hereda usuario. Mantiene su informacion academica y una lista de
 * registros de certificacion.
 */
public class Estudiante extends Usuario {

	/** Rut del estudiante. */
	private String rut;
	/** Nombre completo del estudiante. */
	private String nombreCompleto;
	/** Carrera del estudiante. */
	private String carrera;
	/** Semestre actual del estudiante. */
	private int semestreActual;
	/** Correo electronico del estudiante. */
	private String correoElectronico;

	/** Registros de certificaciones en los que participa el estudiante. */
	private List<RegistroCertificacion> listaRegistrosCertificacion;

	/**
	 * Crea un estudiante con su informacion principal.
	 *
	 * @param rut               rut del estudiante
	 * @param nombreCompleto    nombre completo
	 * @param carrera           carrera
	 * @param semestreActual    semestre actual
	 * @param correoElectronico correo electronico
	 * @param contrasena        contraseña de acceso
	 */
	public Estudiante(String rut, String nombreCompleto, String carrera, int semestreActual, String correoElectronico,
			String contrasena) {
		super(rut, contrasena, Usuario.ROL_ESTUDIANTE);
		this.rut = rut;
		this.nombreCompleto = nombreCompleto;
		this.carrera = carrera;
		this.semestreActual = semestreActual;
		this.correoElectronico = correoElectronico;
		this.listaRegistrosCertificacion = new ArrayList<>();
	}

	/**
	 * Obtiene el rut del estudiante.
	 *
	 * @return rut
	 */
	public String obtenerRut() {
		return rut;
	}

	/**
	 * Obtiene el nombre completo.
	 *
	 * @return nombre completo
	 */
	public String obtenerNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Obtiene la carrera.
	 *
	 * @return carrera
	 */
	public String obtenerCarrera() {
		return carrera;
	}

	/**
	 * Obtiene el semestre actual.
	 *
	 * @return semestre actual
	 */
	public int obtenerSemestreActual() {
		return semestreActual;
	}

	/**
	 * Obtiene el correo electronico.
	 *
	 * @return correo electronico
	 */
	public String obtenerCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * Agrega un registro de certificacion al estudiante.
	 *
	 * @param registro registro de certificacion
	 */
	public void agregarRegistroCertificacion(RegistroCertificacion registro) {
		this.listaRegistrosCertificacion.add(registro);
	}

	/**
	 * Devuelve la lista de registros de certificacion del estudiante.
	 *
	 * @return lista de registros
	 */
	public List<RegistroCertificacion> obtenerListaRegistrosCertificacion() {
		return listaRegistrosCertificacion;
	}
}

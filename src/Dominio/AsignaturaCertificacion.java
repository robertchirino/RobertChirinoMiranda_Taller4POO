package Dominio;

/**
 * Representa la relacion entre una certificacion y una asignatura/curso. Se
 * utiliza para indicar que cursos son requeridos para una certificacion. Los
 * datos normalmente se cargan desde el archivo asignaturas_certificaciones.txt.
 */
public class AsignaturaCertificacion {

	/** Id de la certificacion. */
	private String idCertificacion;
	/** NRC o codigo del curso asociado. */
	private String nrcCurso;

	/**
	 * Crea una relacion certificacion-curso.
	 *
	 * @param idCertificacion id de la certificacion
	 * @param nrcCurso        nrc o codigo del curso
	 */
	public AsignaturaCertificacion(String idCertificacion, String nrcCurso) {
		this.idCertificacion = idCertificacion;
		this.nrcCurso = nrcCurso;
	}

	/**
	 * Obtiene el id de certificacion.
	 *
	 * @return id de certificacion
	 */
	public String obtenerIdCertificacion() {
		return idCertificacion;
	}

	/**
	 * Obtiene el nrc o codigo del curso.
	 *
	 * @return nrc del curso
	 */
	public String obtenerNrcCurso() {
		return nrcCurso;
	}
}

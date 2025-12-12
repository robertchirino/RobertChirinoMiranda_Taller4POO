package Dominio;

/**
 * Representa el registro de un estudiante en una certificacion. Guarda el
 * estado y el porcentaje de progreso.
 */
public class RegistroCertificacion {

	/** Rut del estudiante asociado al registro. */
	private String rutEstudiante;
	/** Id de la certificacion asociada. */
	private String idCertificacion;
	/** Fecha del registro. */
	private String fechaRegistro;
	/** Estado del registro (Activa, Completada, etc.). */
	private String estadoRegistro;
	/** Porcentaje de progreso. */
	private double porcentajeProgreso;

	/**
	 * Crea un registro de certificacion.
	 *
	 * @param rutEstudiante      rut del estudiante
	 * @param idCertificacion    id de certificacion
	 * @param fechaRegistro      fecha en texto
	 * @param estadoRegistro     estado del registro
	 * @param porcentajeProgreso porcentaje de progreso
	 */
	public RegistroCertificacion(String rutEstudiante, String idCertificacion, String fechaRegistro,
			String estadoRegistro, double porcentajeProgreso) {
		this.rutEstudiante = rutEstudiante;
		this.idCertificacion = idCertificacion;
		this.fechaRegistro = fechaRegistro;
		this.estadoRegistro = estadoRegistro;
		this.porcentajeProgreso = porcentajeProgreso;
	}

	/** @return rut del estudiante */
	public String obtenerRutEstudiante() {
		return rutEstudiante;
	}

	/** @return id de certificacion */
	public String obtenerIdCertificacion() {
		return idCertificacion;
	}

	/** @return fecha del registro */
	public String obtenerFechaRegistro() {
		return fechaRegistro;
	}

	/** @return estado del registro */
	public String obtenerEstadoRegistro() {
		return estadoRegistro;
	}

	/** @return porcentaje de progreso */
	public double obtenerPorcentajeProgreso() {
		return porcentajeProgreso;
	}
}

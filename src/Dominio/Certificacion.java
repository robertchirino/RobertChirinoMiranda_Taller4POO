package Dominio;

/**
 * Clase base para una certificacion. Contiene informacion general como id,
 * nombre, descripcion, creditos y validez. Incluye soporte para el patron
 * Visitor mediante aceptarVisitante.
 */
public abstract class Certificacion {

	/** Identificador unico de la certificacion. */
	protected String idCertificacion;
	/** Nombre de la certificacion. */
	protected String nombreCertificacion;
	/** Descripcion de la certificacion. */
	protected String descripcionCertificacion;
	/** Cantidad de creditos requeridos para inscribirse o validar. */
	protected int creditosRequeridos;
	/** Cantidad de años de validez. */
	protected int anosValidez;
	/** Tipo de certificacion (por ejemplo: AVANZADA o TECNICA). */
	protected String tipoCertificacion;

	/**
	 * Crea una certificacion con los datos indicados.
	 *
	 * @param idCertificacion          id de certificacion
	 * @param nombreCertificacion      nombre
	 * @param descripcionCertificacion descripcion
	 * @param creditosRequeridos       creditos requeridos
	 * @param anosValidez              años de validez
	 * @param tipoCertificacion        tipo de certificacion
	 */
	public Certificacion(String idCertificacion, String nombreCertificacion, String descripcionCertificacion,
			int creditosRequeridos, int anosValidez, String tipoCertificacion) {
		this.idCertificacion = idCertificacion;
		this.nombreCertificacion = nombreCertificacion;
		this.descripcionCertificacion = descripcionCertificacion;
		this.creditosRequeridos = creditosRequeridos;
		this.anosValidez = anosValidez;
		this.tipoCertificacion = tipoCertificacion;
	}

	/** @return id de certificacion */
	public String obtenerIdCertificacion() {
		return idCertificacion;
	}

	/** @return nombre de certificacion */
	public String obtenerNombreCertificacion() {
		return nombreCertificacion;
	}

	/** @return descripcion de certificacion */
	public String obtenerDescripcionCertificacion() {
		return descripcionCertificacion;
	}

	/** @return creditos requeridos */
	public int obtenerCreditosRequeridos() {
		return creditosRequeridos;
	}

	/** @return anios de validez */
	public int obtenerAniosValidez() {
		return anosValidez;
	}

	/** @return tipo de certificacion */
	public String obtenerTipoCertificacion() {
		return tipoCertificacion;
	}

	/**
	 * Cambia el nombre de la certificacion.
	 *
	 * @param nombre nuevo nombre
	 */
	public void asignarNombreCertificacion(String nombre) {
		this.nombreCertificacion = nombre;
	}

	/**
	 * Cambia la descripcion de la certificacion.
	 *
	 * @param descripcion nueva descripcion
	 */
	public void asignarDescripcionCertificacion(String descripcion) {
		this.descripcionCertificacion = descripcion;
	}

	/**
	 * Cambia los creditos requeridos.
	 *
	 * @param creditos nuevos creditos
	 */
	public void asignarCreditosRequeridos(int creditos) {
		this.creditosRequeridos = creditos;
	}

	/**
	 * Cambia los anios de validez.
	 *
	 * @param anios nuevos anios de validez
	 */
	public void asignarAniosValidez(int anos) {
		this.anosValidez = anos;
	}

	/**
	 * Metodo del patron Visitor para operar segun el tipo concreto de
	 * certificacion.
	 *
	 * @param visitante visitante que ejecuta la operacion
	 */
	public abstract void aceptarVisitante(VisitanteCertificacion visitante);
}

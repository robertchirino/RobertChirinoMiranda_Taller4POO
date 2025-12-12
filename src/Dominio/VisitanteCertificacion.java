// NOMBRE COMPLETO - RUT - CARRERA
package Dominio;

/**
 * Interfaz del patron Visitor para certificaciones. Permite ejecutar logica
 * distinta dependiendo del tipo concreto.
 */
public interface VisitanteCertificacion {

	/**
	 * Visita una certificacion tecnica.
	 *
	 * @param certificacion certificacion tecnica
	 */
	void visitarCertificacionTecnica(CertificacionTecnica certificacion);

	/**
	 * Visita una certificacion avanzada.
	 *
	 * @param certificacion certificacion avanzada
	 */
	void visitarCertificacionAvanzada(CertificacionAvanzada certificacion);
}

// NOMBRE COMPLETO - RUT - CARRERA
package Dominio;

/**
 * Certificacion de tipo avanzada. Hereda de Certificacion y define su tipo.
 */
public class CertificacionAvanzada extends Certificacion {

	/**
	 * Crea una certificacion avanzada.
	 *
	 * @param idCertificacion          id de certificacion
	 * @param nombreCertificacion      nombre
	 * @param descripcionCertificacion descripcion
	 * @param creditosRequeridos       creditos requeridos
	 * @param anosValidez              años de validez
	 */
	public CertificacionAvanzada(String idCertificacion, String nombreCertificacion, String descripcionCertificacion,
			int creditosRequeridos, int anosValidez) {
		super(idCertificacion, nombreCertificacion, descripcionCertificacion, creditosRequeridos, anosValidez,
				"AVANZADA");
	}

	/**
	 * Acepta un visitante para ejecutar logica segun el tipo de certificacion.
	 *
	 * @param visitante visitante
	 */
	@Override
	public void aceptarVisitante(VisitanteCertificacion visitante) {
		visitante.visitarCertificacionAvanzada(this);
	}
}

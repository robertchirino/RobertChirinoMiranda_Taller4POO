// NOMBRE COMPLETO - RUT - CARRERA
package Dominio;

/**
 * Certificacion de tipo tecnica. Hereda de Certificacion y define su tipo.
 */
public class CertificacionTecnica extends Certificacion {

	/**
	 * Crea una certificacion tecnica.
	 *
	 * @param idCertificacion          id de certificacion
	 * @param nombreCertificacion      nombre
	 * @param descripcionCertificacion descripcion
	 * @param creditosRequeridos       creditos requeridos
	 * @param anosValidez              años de validez
	 */
	public CertificacionTecnica(String idCertificacion, String nombreCertificacion, String descripcionCertificacion,
			int creditosRequeridos, int anosValidez) {
		super(idCertificacion, nombreCertificacion, descripcionCertificacion, creditosRequeridos, anosValidez,
				"TECNICA");
	}

	/**
	 * Acepta un visitante para ejecutar logica segun el tipo de certificacion.
	 *
	 * @param visitante visitante
	 */
	@Override
	public void aceptarVisitante(VisitanteCertificacion visitante) {
		visitante.visitarCertificacionTecnica(this);
	}
}

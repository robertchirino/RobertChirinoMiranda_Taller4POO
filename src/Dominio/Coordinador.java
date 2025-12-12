// NOMBRE COMPLETO - RUT - CARRERA
package Dominio;

/**
 * Representa un usuario coordinador. El coordinador tiene un area de
 * coordinacion asociada.
 */
public class Coordinador extends Usuario {

	/** Area a cargo del coordinador. */
	private String areaCoordinacion;

	/**
	 * Crea un coordinador con su nombre de usuario, contraseña y area.
	 *
	 * @param nombreUsuario nombre de usuario del coordinador
	 * @param contrasena    contraseña del coordinador
	 * @param area          area de coordinacion
	 */
	public Coordinador(String nombreUsuario, String contrasena, String area) {
		super(nombreUsuario, contrasena, Usuario.ROL_COORDINADOR);
		this.areaCoordinacion = area;
	}

	/**
	 * Obtiene el area de coordinacion.
	 *
	 * @return area de coordinacion
	 */
	public String obtenerAreaCoordinacion() {
		return areaCoordinacion;
	}

	/**
	 * Cambia el area de coordinacion.
	 *
	 * @param area nueva area
	 */
	public void asignarAreaCoordinacion(String area) {
		this.areaCoordinacion = area;
	}
}

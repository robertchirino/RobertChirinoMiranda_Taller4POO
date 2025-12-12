// NOMBRE COMPLETO - RUT - CARRERA
package Dominio;

/**
 * Clase base para los usuarios del sistema. Un usuario tiene nombre de usuario,
 * contrasena y rol. Esta clase se hereda para crear tipos concretos de usuario.
 */
public abstract class Usuario {

	/** Rol para administrador. */
	public static final String ROL_ADMINISTRADOR = "Admin";
	/** Rol para coordinador. */
	public static final String ROL_COORDINADOR = "Coordinador";
	/** Rol para estudiante. */
	public static final String ROL_ESTUDIANTE = "Estudiante";

	/** Identificador del usuario (puede ser rut o nombre de usuario). */
	protected String nombreUsuario;
	/** Contraseña del usuario. */
	protected String contrasena;
	/** Rol del usuario dentro del sistema. */
	protected String rol;

	/**
	 * Crea un usuario con los datos indicados.
	 *
	 * @param nombreUsuario nombre de usuario (o rut si aplica)
	 * @param contrasena    contraseña del usuario
	 * @param rol           rol del usuario
	 */
	public Usuario(String nombreUsuario, String contrasena, String rol) {
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.rol = rol;
	}

	/**
	 * Obtiene el nombre de usuario.
	 *
	 * @return nombre de usuario
	 */
	public String obtenerNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Obtiene la contraseña.
	 *
	 * @return contrasena
	 */
	public String obtenerContrasena() {
		return contrasena;
	}

	/**
	 * Obtiene el rol del usuario.
	 *
	 * @return rol
	 */
	public String obtenerRol() {
		return rol;
	}

	/**
	 * Cambia la contraseña del usuario.
	 *
	 * @param nuevaContrasena nueva contrasena
	 */
	public void asignarContrasena(String nuevaContrasena) {
		this.contrasena = nuevaContrasena;
	}

	/**
	 * Representacion simple del usuario para depuracion.
	 *
	 * @return texto con datos del usuario
	 */
	@Override
	public String toString() {
		return "Usuario[" + "nombreUsuario='" + nombreUsuario + '\'' + ", rol='" + rol + '\'' + ']';
	}
}

package Dominio;

/**
 * Representa un usuario administrador del sistema. El administrador puede
 * realizar operaciones de administracion como crear, modificar o eliminar
 * usuarios.
 */
public class Administrador extends Usuario {

	/**
	 * Crea un administrador con nombre de usuario y contraseña.
	 *
	 * @param nombreUsuario nombre de usuario del administrador
	 * @param contrasena    contraseña del administrador
	 */
	public Administrador(String nombreUsuario, String contrasena) {
		super(nombreUsuario, contrasena, Usuario.ROL_ADMINISTRADOR);
	}

}

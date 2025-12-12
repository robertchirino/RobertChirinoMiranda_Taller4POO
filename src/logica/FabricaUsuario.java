package logica;

import Dominio.Administrador;
import Dominio.Coordinador;
import Dominio.Usuario;

/**
 * Fabrica para construir usuarios desde el archivo usuarios.txt. NOTA: Los
 * estudiantes no se crean aqui, porque se cargan desde estudiantes.txt.
 */
public class FabricaUsuario {

	/**
	 * Crea un usuario a partir de una linea del archivo usuarios.txt.
	 *
	 * Formato esperado: usuario;contrasena;rol;area(opcional si rol = Coordinador)
	 *
	 * Ejemplos: admin;1234;Admin pedro;pass123;Coordinador;Ingenieria
	 *
	 * @param partes arreglo con los campos separados por ";"
	 * @return Usuario creado (Administrador o Coordinador) o null si no es valido
	 */
	public static Usuario crearUsuarioDesdeLinea(String[] partes) {

		if (partes.length < 3) {
			return null;
		}

		String nombreUsuario = partes[0];
		String contrasena = partes[1];
		String rol = partes[2];

		// Administrador
		if (rol.equalsIgnoreCase(Usuario.ROL_ADMINISTRADOR)) {
			return new Administrador(nombreUsuario, contrasena);
		}

		// Coordinador
		if (rol.equalsIgnoreCase(Usuario.ROL_COORDINADOR)) {
			String area = "";
			if (partes.length >= 4) {
				area = partes[3];
			}
			return new Coordinador(nombreUsuario, contrasena, area);
		}

		return null;
	}
}

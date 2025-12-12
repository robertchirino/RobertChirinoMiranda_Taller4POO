package GUI;

import Dominio.*;
import logica.GestorDatos;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del administrador. Permite gestionar usuarios del sistema.
 */
public class VentanaAdministrador extends JFrame {

	private Administrador administrador;

	/**
	 * Constructor de la ventana del administrador.
	 *
	 * @param administrador usuario administrador autenticado
	 */
	public VentanaAdministrador(Administrador administrador) {
		this.administrador = administrador;
		setTitle("Menu Administrador");
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		crearComponentes();
	}

	/**
	 * Crea los botones del menu administrador.
	 */
	private void crearComponentes() {
		JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));

		JButton botonCrear = new JButton("Crear usuario");
		JButton botonModificar = new JButton("Modificar usuario");
		JButton botonEliminar = new JButton("Eliminar usuario");
		JButton botonRestablecer = new JButton("Restablecer contrasena");
		JButton botonVerEstudiantes = new JButton("Ver estudiantes");

		botonCrear.addActionListener(e -> crearUsuario());
		botonModificar.addActionListener(e -> modificarCuenta());
		botonEliminar.addActionListener(e -> eliminarUsuario());
		botonRestablecer.addActionListener(e -> restablecerContrasena());
		botonVerEstudiantes.addActionListener(e -> verEstudiantes());

		panel.add(botonCrear);
		panel.add(botonModificar);
		panel.add(botonEliminar);
		panel.add(botonRestablecer);
		panel.add(botonVerEstudiantes);

		add(panel);
	}

	/**
	 * Permite crear un estudiante o coordinador.
	 */
	private void crearUsuario() {
		String[] opciones = { "Estudiante", "Coordinador" };
		int opcion = JOptionPane.showOptionDialog(this, "Seleccione tipo de usuario:", "Crear usuario",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		GestorDatos gestor = GestorDatos.obtenerInstancia();

		if (opcion == 0) {
			String rut = JOptionPane.showInputDialog(this, "RUT:");
			String nombre = JOptionPane.showInputDialog(this, "Nombre completo:");
			String carrera = JOptionPane.showInputDialog(this, "Carrera:");
			String semestreTexto = JOptionPane.showInputDialog(this, "Semestre:");
			String correo = JOptionPane.showInputDialog(this, "Correo:");
			String contrasena = JOptionPane.showInputDialog(this, "Contrasena:");

			try {
				int semestre = Integer.parseInt(semestreTexto);
				gestor.crearUsuarioEstudiante(rut, nombre, carrera, semestre, correo, contrasena);
				JOptionPane.showMessageDialog(this, "Estudiante creado correctamente");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Semestre invalido");
			}
		} else if (opcion == 1) {
			String usuario = JOptionPane.showInputDialog(this, "Usuario:");
			String contrasena = JOptionPane.showInputDialog(this, "Contrasena:");
			String area = JOptionPane.showInputDialog(this, "Area:");

			gestor.crearUsuarioCoordinador(usuario, contrasena, area);
			JOptionPane.showMessageDialog(this, "Coordinador creado correctamente");
		}
	}

	/**
	 * Permite modificar un usuario existente.
	 */
	private void modificarCuenta() {
		String usuario = JOptionPane.showInputDialog(this, "Ingrese RUT (estudiante) o usuario (coordinador):");
		if (usuario == null || usuario.trim().isEmpty())
			return;

		GestorDatos gestor = GestorDatos.obtenerInstancia();

		if (gestor.buscarEstudiantePorRut(usuario) != null) {
			String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre completo:");
			String nuevaCarrera = JOptionPane.showInputDialog(this, "Nueva carrera:");
			String nuevoSemestreTexto = JOptionPane.showInputDialog(this, "Nuevo semestre:");
			String nuevoCorreo = JOptionPane.showInputDialog(this, "Nuevo correo:");

			try {
				int nuevoSemestre = Integer.parseInt(nuevoSemestreTexto);
				gestor.modificarUsuarioEstudiante(usuario, nuevoNombre, nuevaCarrera, nuevoSemestre, nuevoCorreo);
				JOptionPane.showMessageDialog(this, "Estudiante modificado correctamente");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Semestre invalido");
			}
		} else {
			String nuevaContrasena = JOptionPane.showInputDialog(this, "Nueva contrasena (opcional):");
			String nuevaArea = JOptionPane.showInputDialog(this, "Nueva area (opcional):");
			gestor.modificarUsuarioCoordinador(usuario, nuevaContrasena, nuevaArea);
			JOptionPane.showMessageDialog(this, "Coordinador modificado");
		}
	}

	/**
	 * Elimina un usuario del sistema.
	 */
	private void eliminarUsuario() {
		String usuario = JOptionPane.showInputDialog(this, "Ingrese usuario o rut:");
		if (usuario == null || usuario.trim().isEmpty())
			return;

		GestorDatos gestor = GestorDatos.obtenerInstancia();
		gestor.eliminarUsuario(usuario);
		JOptionPane.showMessageDialog(this, "Usuario eliminado (si existia)");
	}

	/**
	 * Restablece la contraseña de un usuario.
	 */
	private void restablecerContrasena() {
		String usuario = JOptionPane.showInputDialog(this, "Ingrese usuario o rut:");
		if (usuario == null || usuario.trim().isEmpty())
			return;

		String nueva = JOptionPane.showInputDialog(this, "Ingrese nueva contrasena:");
		if (nueva == null || nueva.trim().isEmpty())
			return;

		GestorDatos gestor = GestorDatos.obtenerInstancia();
		gestor.restablecerContrasena(usuario, nueva);

		JOptionPane.showMessageDialog(this, "Contrasena restablecida");
	}

	/**
	 * Muestra el listado de estudiantes.
	 */
	private void verEstudiantes() {
		GestorDatos gestor = GestorDatos.obtenerInstancia();

		String texto = "Listado de estudiantes:\n\n";
		for (Estudiante e : gestor.obtenerEstudiantes()) {
			texto += e.obtenerRut() + " - " + e.obtenerNombreCompleto() + " - " + e.obtenerCarrera() + "\n";
		}

		JOptionPane.showMessageDialog(this, texto, "Estudiantes", JOptionPane.INFORMATION_MESSAGE);
	}
}

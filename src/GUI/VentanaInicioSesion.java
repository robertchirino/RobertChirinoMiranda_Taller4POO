package GUI;

import Dominio.*;
import logica.GestorDatos;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana inicial del sistema. Permite a un usuario iniciar sesion como
 * administrador, coordinador o estudiante.
 */
public class VentanaInicioSesion extends JFrame {

	private JTextField campoUsuario;
	private JPasswordField campoContrasena;

	/**
	 * Crea la ventana de inicio de sesion.
	 */
	public VentanaInicioSesion() {
		setTitle("Inicio de sesion");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		crearComponentes();
	}

	/**
	 * Crea y organiza los componentes de la interfaz.
	 */
	private void crearComponentes() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

		panel.add(new JLabel("Usuario o RUT:"));
		campoUsuario = new JTextField();
		panel.add(campoUsuario);

		panel.add(new JLabel("Contrasena:"));
		campoContrasena = new JPasswordField();
		panel.add(campoContrasena);

		JButton botonIngresar = new JButton("Ingresar");
		botonIngresar.addActionListener(e -> iniciarSesion());

		panel.add(new JLabel());
		panel.add(botonIngresar);

		add(panel);
	}

	/**
	 * Valida las credenciales y abre la ventana correspondiente segun el rol.
	 */
	private void iniciarSesion() {
		String usuario = campoUsuario.getText();
		String contrasena = new String(campoContrasena.getPassword());

		GestorDatos gestor = GestorDatos.obtenerInstancia();
		Usuario u = gestor.iniciarSesion(usuario, contrasena);

		if (u == null) {
			JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
			return;
		}

		dispose();

		if (u instanceof Administrador) {
			new VentanaAdministrador((Administrador) u).setVisible(true);
		} else if (u instanceof Coordinador) {
			new VentanaCoordinador((Coordinador) u).setVisible(true);
		} else if (u instanceof Estudiante) {
			new VentanaEstudiante((Estudiante) u).setVisible(true);
		}
	}
}

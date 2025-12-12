package GUI;

import Dominio.*;
import logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Ventana principal del estudiante. Permite ver perfil, notas, promedios y
 * certificaciones.
 */
public class VentanaEstudiante extends JFrame {

	private Estudiante estudiante;

	/**
	 * Constructor de la ventana del estudiante.
	 *
	 * @param estudiante estudiante autenticado
	 */
	public VentanaEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
		setTitle("Menu Estudiante - " + estudiante.obtenerNombreCompleto());
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		crearComponentes();
	}

	/**
	 * Crea los botones del menu estudiante.
	 */
	private void crearComponentes() {
		JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

		JButton botonPerfil = new JButton("Ver perfil");
		JButton botonNotas = new JButton("Ver notas");
		JButton botonPromedio = new JButton("Calcular promedio");
		JButton botonCertificaciones = new JButton("Ver certificaciones");

		botonPerfil.addActionListener(e -> verPerfil());
		botonNotas.addActionListener(e -> verNotas());
		botonPromedio.addActionListener(e -> calcularPromedio());
		botonCertificaciones.addActionListener(e -> verCertificaciones());

		panel.add(botonPerfil);
		panel.add(botonNotas);
		panel.add(botonPromedio);
		panel.add(botonCertificaciones);

		add(panel);
	}

	/**
	 * Muestra el perfil del estudiante.
	 */
	private void verPerfil() {
		String texto = "RUT: " + estudiante.obtenerRut() + "\n" + "Nombre: " + estudiante.obtenerNombreCompleto() + "\n"
				+ "Carrera: " + estudiante.obtenerCarrera() + "\n" + "Semestre: " + estudiante.obtenerSemestreActual()
				+ "\n" + "Correo: " + estudiante.obtenerCorreoElectronico();

		JOptionPane.showMessageDialog(this, texto, "Perfil", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Muestra las notas del estudiante.
	 */
	private void verNotas() {
		GestorDatos gestor = GestorDatos.obtenerInstancia();
		List<Nota> notas = gestor.obtenerNotasPorRut(estudiante.obtenerRut());

		if (notas.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay notas registradas");
			return;
		}

		String texto = "Notas:\n\n";
		for (Nota n : notas) {
			texto += n.obtenerCodigoAsignatura() + " - " + n.obtenerCalificacion() + " (" + n.obtenerEstadoAsignatura()
					+ ")\n";
		}

		JOptionPane.showMessageDialog(this, texto);
	}

	/**
	 * Calcula el promedio del estudiante.
	 */
	private void calcularPromedio() {
		GestorDatos gestor = GestorDatos.obtenerInstancia();
		List<Nota> notas = gestor.obtenerNotasPorRut(estudiante.obtenerRut());

		if (notas.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay notas para calcular promedio");
			return;
		}

		EstrategiaCalculoPromedio estrategia = new EstrategiaPromedioGeneral();
		double promedio = estrategia.calcularPromedio(notas);

		JOptionPane.showMessageDialog(this, "Promedio general: " + promedio);
	}

	/**
	 * Muestra las certificaciones disponibles.
	 */
	private void verCertificaciones() {
		GestorDatos gestor = GestorDatos.obtenerInstancia();

		String texto = "Certificaciones:\n\n";
		for (Certificacion c : gestor.obtenerCertificaciones()) {
			texto += c.obtenerIdCertificacion() + " - " + c.obtenerNombreCertificacion() + "\n";
		}

		JOptionPane.showMessageDialog(this, texto);
	}
}

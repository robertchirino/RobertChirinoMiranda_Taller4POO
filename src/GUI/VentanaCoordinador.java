package GUI;

import Dominio.*;
import logica.GestorDatos;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Ventana principal del coordinador. Permite gestionar certificaciones,
 * metricas y estudiantes.
 */
public class VentanaCoordinador extends JFrame {

	private Coordinador coordinador;

	/**
	 * Constructor de la ventana del coordinador.
	 *
	 * @param coordinador coordinador autenticado
	 */
	public VentanaCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
		setTitle("Menu Coordinador - " + coordinador.obtenerNombreUsuario() + " ("
				+ coordinador.obtenerAreaCoordinacion() + ")");
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		crearComponentes();
	}

	/**
	 * Crea los botones del menu coordinador.
	 */
	private void crearComponentes() {
		JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

		JButton botonCertificaciones = new JButton("Gestion de certificaciones");
		JButton botonMetricas = new JButton("Panel de metricas y analisis");
		JButton botonEstudiantes = new JButton("Gestion de estudiantes");
		JButton botonJavaFx = new JButton("Abrir panel JavaFX");
		botonJavaFx.addActionListener(e -> VentanaFx.abrirVentanaFx());
		panel.add(botonJavaFx);

		botonCertificaciones.addActionListener(e -> gestionarCertificaciones());
		botonMetricas.addActionListener(e -> mostrarMetricas());
		botonEstudiantes.addActionListener(e -> gestionarEstudiantes());

		panel.add(botonCertificaciones);
		panel.add(botonMetricas);
		panel.add(botonEstudiantes);

		add(panel, BorderLayout.CENTER);
	}

	/**
	 * Gestiona opciones relacionadas con certificaciones.
	 */
	private void gestionarCertificaciones() {
		GestorDatos gestor = GestorDatos.obtenerInstancia();

		Object[] opciones = { "Modificar certificacion", "Generar certificados", "Ver listado" };

		int opcion = JOptionPane.showOptionDialog(this, "Seleccione una opcion:", "Gestion de certificaciones",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		if (opcion == 0) {
			modificarCertificacionDesdeCoordinador(gestor);
		} else if (opcion == 1) {
			generarCertificadosDesdeCoordinador(gestor);
		} else if (opcion == 2) {
			mostrarListadoCertificaciones(gestor);
		}
	}

	/**
	 * Muestra el listado de certificaciones.
	 */
	private void mostrarListadoCertificaciones(GestorDatos gestor) {
		String texto = "Certificaciones disponibles:\n\n";
		for (Certificacion c : gestor.obtenerCertificaciones()) {
			texto += c.obtenerIdCertificacion() + " - " + c.obtenerNombreCertificacion() + " (Creditos: "
					+ c.obtenerCreditosRequeridos() + ", Validez: " + c.obtenerAniosValidez() + " anios)\n";
		}
		JOptionPane.showMessageDialog(this, texto, "Certificaciones", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Permite modificar una certificacion.
	 */
	private void modificarCertificacionDesdeCoordinador(GestorDatos gestor) {
		String id = JOptionPane.showInputDialog(this, "Ingrese ID de la certificacion:");
		if (id == null || id.trim().isEmpty())
			return;

		Certificacion cert = gestor.buscarCertificacionPorId(id);
		if (cert == null) {
			JOptionPane.showMessageDialog(this, "Certificacion no encontrada");
			return;
		}

		String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", cert.obtenerNombreCertificacion());
		String nuevaDescripcion = JOptionPane.showInputDialog(this, "Nueva descripcion:",
				cert.obtenerDescripcionCertificacion());

		gestor.modificarCertificacion(id, nuevoNombre, nuevaDescripcion, cert.obtenerCreditosRequeridos(),
				cert.obtenerAniosValidez());

		JOptionPane.showMessageDialog(this, "Certificacion modificada");
	}

	/**
	 * Genera certificados para estudiantes que completaron una certificacion.
	 */
	private void generarCertificadosDesdeCoordinador(GestorDatos gestor) {
		String id = JOptionPane.showInputDialog(this, "Ingrese ID de certificacion:");
		if (id == null || id.trim().isEmpty())
			return;

		String archivo = gestor.generarArchivoCertificados(id);
		JOptionPane.showMessageDialog(this, "Archivo generado: " + archivo);
	}

	/**
	 * Muestra metricas del sistema.
	 */
	private void mostrarMetricas() {
		GestorDatos gestor = GestorDatos.obtenerInstancia();

		Map<String, Integer> inscripciones = gestor.calcularInscripcionesPorCertificacion();
		Map<String, Integer> reprobaciones = gestor.calcularReprobacionesPorCurso();

		String texto = "Metricas:\n\n";

		for (String id : inscripciones.keySet()) {
			texto += "Certificacion " + id + ": " + inscripciones.get(id) + " inscripciones\n";
		}

		texto += "\nReprobaciones por curso:\n";
		for (String c : reprobaciones.keySet()) {
			texto += "Curso " + c + ": " + reprobaciones.get(c) + "\n";
		}

		JOptionPane.showMessageDialog(this, texto);
	}

	/**
	 * Permite consultar informacion de un estudiante.
	 */
	private void gestionarEstudiantes() {
		GestorDatos gestor = GestorDatos.obtenerInstancia();
		String rut = JOptionPane.showInputDialog(this, "Ingrese RUT del estudiante:");
		if (rut == null || rut.trim().isEmpty())
			return;

		Estudiante est = gestor.buscarEstudiantePorRut(rut);
		if (est == null) {
			JOptionPane.showMessageDialog(this, "Estudiante no encontrado");
			return;
		}

		String texto = "RUT: " + est.obtenerRut() + "\n" + "Nombre: " + est.obtenerNombreCompleto() + "\n" + "Carrera: "
				+ est.obtenerCarrera() + "\n" + "Semestre: " + est.obtenerSemestreActual();

		JOptionPane.showMessageDialog(this, texto);
	}
}

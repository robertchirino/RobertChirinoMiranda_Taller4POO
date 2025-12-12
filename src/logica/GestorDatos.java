package logica;

import Dominio.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Clase Singleton que gestiona todos los datos del sistema: usuarios,
 * estudiantes, cursos, certificaciones, notas, registros, etc. Se encarga de
 * cargar y guardar archivos de texto.
 */
public class GestorDatos {

	/** Instancia unica del Singleton. */
	private static GestorDatos instancia;

	// Rutas de archivos
	private static final String RUTA_USUARIOS = "usuarios.txt";
	private static final String RUTA_ESTUDIANTES = "estudiantes.txt";
	private static final String RUTA_CURSOS = "cursos.txt";
	private static final String RUTA_CERTIFICACIONES = "certificaciones.txt";
	private static final String RUTA_REGISTROS = "registros.txt";
	private static final String RUTA_NOTAS = "notas.txt";
	private static final String RUTA_ASIGNATURAS_CERTIFICACIONES = "asignaturas_certificaciones.txt";

	/** Mapa de usuarios (admin y coordinadores, y tambien estudiantes por rut). */
	private Map<String, Usuario> mapaUsuarios;
	/** Mapa de estudiantes por rut. */
	private Map<String, Estudiante> mapaEstudiantes;
	/** Mapa de cursos por nrc. */
	private Map<String, Curso> mapaCursos;
	/** Mapa de certificaciones por id. */
	private Map<String, Certificacion> mapaCertificaciones;

	/** Lista de registros de certificacion. */
	private List<RegistroCertificacion> listaRegistros;
	/** Lista de notas. */
	private List<Nota> listaNotas;
	/** Lista de relacion certificacion-curso. */
	private List<AsignaturaCertificacion> listaAsignaturasCertificacion;

	/**
	 * Constructor privado del Singleton. Inicializa estructuras y carga archivos.
	 */
	private GestorDatos() {
		mapaUsuarios = new LinkedHashMap<>();
		mapaEstudiantes = new LinkedHashMap<>();
		mapaCursos = new LinkedHashMap<>();
		mapaCertificaciones = new LinkedHashMap<>();
		listaRegistros = new ArrayList<>();
		listaNotas = new ArrayList<>();
		listaAsignaturasCertificacion = new ArrayList<>();

		cargarArchivos();
	}

	/**
	 * Punto de acceso unico al Singleton.
	 *
	 * @return instancia unica de GestorDatos
	 */
	public static GestorDatos obtenerInstancia() {
		if (instancia == null) {
			instancia = new GestorDatos();
		}
		return instancia;
	}

	// ---------------------------------------------------------
	// CARGA DE ARCHIVOS
	// ---------------------------------------------------------

	/**
	 * Carga todos los archivos necesarios del sistema.
	 */
	private void cargarArchivos() {
		cargarUsuarios();
		cargarEstudiantes();
		cargarCursos();
		cargarCertificaciones();
		cargarRegistros();
		cargarNotas();
		cargarAsignaturasCertificaciones();
	}

	/**
	 * Carga usuarios desde usuarios.txt usando FabricaUsuario.
	 */
	private void cargarUsuarios() {
		try (BufferedReader lector = Files.newBufferedReader(Paths.get(RUTA_USUARIOS))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				if (linea.trim().isEmpty()) {
					continue;
				}
				String[] partes = linea.split(";");
				Usuario usuario = FabricaUsuario.crearUsuarioDesdeLinea(partes);
				if (usuario != null) {
					mapaUsuarios.put(usuario.obtenerNombreUsuario(), usuario);
				}
			}
		} catch (IOException e) {
			System.err.println("Error al cargar usuarios: " + e.getMessage());
		}
	}

	/**
	 * Carga estudiantes desde estudiantes.txt. Los estudiantes tambien se agregan a
	 * mapaUsuarios para login por rut.
	 */
	private void cargarEstudiantes() {
		try (BufferedReader lector = Files.newBufferedReader(Paths.get(RUTA_ESTUDIANTES))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				if (linea.trim().isEmpty()) {
					continue;
				}

				// rut;nombre;carrera;semestre;correo;contraseña
				String[] partes = linea.split(";");
				if (partes.length < 6) {
					continue;
				}

				String rut = partes[0];
				String nombre = partes[1];
				String carrera = partes[2];
				int semestre = Integer.parseInt(partes[3]);
				String correo = partes[4];
				String contrasena = partes[5];

				Estudiante estudiante = new Estudiante(rut, nombre, carrera, semestre, correo, contrasena);

				mapaEstudiantes.put(rut, estudiante);
				mapaUsuarios.put(rut, estudiante);
			}
		} catch (IOException e) {
			System.err.println("Error al cargar estudiantes: " + e.getMessage());
		}
	}

	/**
	 * Carga cursos desde cursos.txt.
	 */
	private void cargarCursos() {
		try (BufferedReader lector = Files.newBufferedReader(Paths.get(RUTA_CURSOS))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				if (linea.trim().isEmpty()) {
					continue;
				}

				// NRC;nombre;semestre;creditos;area;requisito1,requisito2
				String[] partes = linea.split(";");
				if (partes.length < 5) {
					continue;
				}

				String nrc = partes[0];
				String nombre = partes[1];
				int semestre = Integer.parseInt(partes[2]);
				int creditos = Integer.parseInt(partes[3]);
				String area = partes[4];

				List<String> prerrequisitos = new ArrayList<>();
				if (partes.length >= 6 && !partes[5].isEmpty()) {
					prerrequisitos = Arrays.asList(partes[5].split(","));
				}

				Curso curso = new Curso(nrc, nombre, semestre, creditos, area, prerrequisitos);
				mapaCursos.put(nrc, curso);
			}
		} catch (IOException e) {
			System.err.println("Error al cargar cursos: " + e.getMessage());
		}
	}

	/**
	 * Carga certificaciones desde certificaciones.txt.
	 */
	private void cargarCertificaciones() {
		try (BufferedReader lector = Files.newBufferedReader(Paths.get(RUTA_CERTIFICACIONES))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				if (linea.trim().isEmpty()) {
					continue;
				}

				// ID;nombre;descripcion;creditosrequeridos;añosvalidez
				String[] partes = linea.split(";");
				if (partes.length < 5) {
					continue;
				}

				String id = partes[0];
				String nombre = partes[1];
				String descripcion = partes[2];
				int creditos = Integer.parseInt(partes[3]);
				int anosValidez = Integer.parseInt(partes[4]);

				Certificacion certificacion;
				if (nombre.toLowerCase().contains("avanzada")) {
					certificacion = new CertificacionAvanzada(id, nombre, descripcion, creditos, anosValidez);
				} else {
					certificacion = new CertificacionTecnica(id, nombre, descripcion, creditos, anosValidez);
				}

				mapaCertificaciones.put(id, certificacion);
			}
		} catch (IOException e) {
			System.err.println("Error al cargar certificaciones: " + e.getMessage());
		}
	}

	/**
	 * Carga registros desde registros.txt y los asocia al estudiante si existe.
	 */
	private void cargarRegistros() {
		try (BufferedReader lector = Files.newBufferedReader(Paths.get(RUTA_REGISTROS))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				if (linea.trim().isEmpty()) {
					continue;
				}

				// rut;ID;fecha;estado;progreso
				String[] partes = linea.split(";");
				if (partes.length < 5) {
					continue;
				}

				String rut = partes[0];
				String id = partes[1];
				String fecha = partes[2];
				String estado = partes[3];
				double progreso = Double.parseDouble(partes[4]);

				RegistroCertificacion registro = new RegistroCertificacion(rut, id, fecha, estado, progreso);
				listaRegistros.add(registro);

				Estudiante est = mapaEstudiantes.get(rut);
				if (est != null) {
					est.agregarRegistroCertificacion(registro);
				}
			}
		} catch (IOException e) {
			System.err.println("Error al cargar registros: " + e.getMessage());
		}
	}

	/**
	 * Carga notas desde notas.txt.
	 */
	private void cargarNotas() {
		try (BufferedReader lector = Files.newBufferedReader(Paths.get(RUTA_NOTAS))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				if (linea.trim().isEmpty()) {
					continue;
				}

				// rut;codigo;calificacion;estado;semestre
				String[] partes = linea.split(";");
				if (partes.length < 5) {
					continue;
				}

				String rut = partes[0];
				String codigo = partes[1];
				double calificacion = Double.parseDouble(partes[2]);
				String estado = partes[3];
				String semestre = partes[4];

				Nota nota = new Nota(rut, codigo, calificacion, estado, semestre);
				listaNotas.add(nota);
			}
		} catch (IOException e) {
			System.err.println("Error al cargar notas: " + e.getMessage());
		}
	}

	/**
	 * Carga relaciones certificacion-curso desde asignaturas_certificaciones.txt.
	 */
	private void cargarAsignaturasCertificaciones() {
		try (BufferedReader lector = Files.newBufferedReader(Paths.get(RUTA_ASIGNATURAS_CERTIFICACIONES))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				if (linea.trim().isEmpty()) {
					continue;
				}

				// ID;NRC
				String[] partes = linea.split(";");
				if (partes.length < 2) {
					continue;
				}

				String id = partes[0];
				String nrc = partes[1];

				AsignaturaCertificacion ac = new AsignaturaCertificacion(id, nrc);
				listaAsignaturasCertificacion.add(ac);
			}
		} catch (IOException e) {
			System.err.println("Error al cargar asignaturas_certificaciones: " + e.getMessage());
		}
	}

	// ---------------------------------------------------------
	// LOGIN
	// ---------------------------------------------------------

	/**
	 * Valida credenciales para iniciar sesion.
	 *
	 * @param nombreUsuario nombre de usuario (o rut)
	 * @param contrasena    contrasena
	 * @return Usuario si es valido, o null si no coincide
	 */
	public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
		Usuario u = mapaUsuarios.get(nombreUsuario);
		if (u != null && u.obtenerContrasena().equals(contrasena)) {
			return u;
		}
		return null;
	}

	// ---------------------------------------------------------
	// CREAR / EDITAR (ADMIN)
	// ---------------------------------------------------------

	/**
	 * Crea un estudiante y actualiza estudiantes.txt.
	 */
	public void crearUsuarioEstudiante(String rut, String nombre, String carrera, int semestre, String correo,
			String contrasena) {
		Estudiante estudiante = new Estudiante(rut, nombre, carrera, semestre, correo, contrasena);
		mapaEstudiantes.put(rut, estudiante);
		mapaUsuarios.put(rut, estudiante);
		guardarEstudiantesEnArchivo();
	}

	/**
	 * Crea un coordinador y actualiza usuarios.txt.
	 */
	public void crearUsuarioCoordinador(String nombreUsuario, String contrasena, String area) {
		Coordinador coordinador = new Coordinador(nombreUsuario, contrasena, area);
		mapaUsuarios.put(nombreUsuario, coordinador);
		guardarUsuariosEnArchivo();
	}

	/**
	 * Modifica un estudiante y mantiene sus registros de certificacion.
	 */
	public void modificarUsuarioEstudiante(String rut, String nuevoNombre, String nuevaCarrera, int nuevoSemestre,
			String nuevoCorreo) {
		Estudiante est = mapaEstudiantes.get(rut);
		if (est != null) {
			String contrasena = est.obtenerContrasena();
			Estudiante nuevo = new Estudiante(rut, nuevoNombre, nuevaCarrera, nuevoSemestre, nuevoCorreo, contrasena);

			for (RegistroCertificacion reg : est.obtenerListaRegistrosCertificacion()) {
				nuevo.agregarRegistroCertificacion(reg);
			}

			mapaEstudiantes.put(rut, nuevo);
			mapaUsuarios.put(rut, nuevo);
			guardarEstudiantesEnArchivo();
		}
	}

	/**
	 * Modifica campos opcionales del coordinador.
	 */
	public void modificarUsuarioCoordinador(String nombreUsuario, String nuevaContrasena, String nuevaArea) {
		Usuario u = mapaUsuarios.get(nombreUsuario);
		if (u instanceof Coordinador) {
			Coordinador c = (Coordinador) u;
			if (nuevaContrasena != null && !nuevaContrasena.isEmpty()) {
				c.asignarContrasena(nuevaContrasena);
			}
			if (nuevaArea != null && !nuevaArea.isEmpty()) {
				c.asignarAreaCoordinacion(nuevaArea);
			}
			guardarUsuariosEnArchivo();
		}
	}

	/**
	 * Elimina un usuario. Si es estudiante, tambien limpia notas y registros.
	 */
	public void eliminarUsuario(String nombreUsuario) {
		Usuario u = mapaUsuarios.remove(nombreUsuario);
		if (u == null) {
			return;
		}

		if (u instanceof Estudiante) {
			Estudiante est = (Estudiante) u;
			mapaEstudiantes.remove(est.obtenerRut());
			eliminarReferenciasEstudiante(est.obtenerRut());
			guardarEstudiantesEnArchivo();
		} else if (u instanceof Coordinador) {
			guardarUsuariosEnArchivo();
		}
	}

	/**
	 * Elimina registros y notas asociados a un rut y guarda archivos.
	 */
	private void eliminarReferenciasEstudiante(String rut) {
		listaRegistros.removeIf(r -> r.obtenerRutEstudiante().equals(rut));
		listaNotas.removeIf(n -> n.obtenerRutEstudiante().equals(rut));
		guardarRegistrosEnArchivo();
		guardarNotasEnArchivo();
	}

	/**
	 * Cambia la contraseña de un usuario y guarda en el archivo correspondiente.
	 */
	public void restablecerContrasena(String nombreUsuario, String nuevaContrasena) {
		Usuario u = mapaUsuarios.get(nombreUsuario);
		if (u != null) {
			u.asignarContrasena(nuevaContrasena);
			if (u instanceof Estudiante) {
				guardarEstudiantesEnArchivo();
			} else {
				guardarUsuariosEnArchivo();
			}
		}
	}

	// ---------------------------------------------------------
	// GUARDAR ARCHIVOS
	// ---------------------------------------------------------

	/**
	 * Guarda usuarios (Administrador y Coordinador) en usuarios.txt.
	 */
	private void guardarUsuariosEnArchivo() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_USUARIOS))) {
			for (Usuario u : mapaUsuarios.values()) {
				if (u instanceof Administrador || u instanceof Coordinador) {
					String linea = u.obtenerNombreUsuario() + ";" + u.obtenerContrasena() + ";" + u.obtenerRol();
					if (u instanceof Coordinador) {
						Coordinador c = (Coordinador) u;
						linea += ";" + c.obtenerAreaCoordinacion();
					}
					escritor.write(linea);
					escritor.newLine();
				}
			}
		} catch (IOException e) {
			System.err.println("Error al guardar usuarios: " + e.getMessage());
		}
	}

	/**
	 * Guarda estudiantes en estudiantes.txt.
	 */
	private void guardarEstudiantesEnArchivo() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_ESTUDIANTES))) {
			for (Estudiante e : mapaEstudiantes.values()) {
				String linea = e.obtenerRut() + ";" + e.obtenerNombreCompleto() + ";" + e.obtenerCarrera() + ";"
						+ e.obtenerSemestreActual() + ";" + e.obtenerCorreoElectronico() + ";" + e.obtenerContrasena();
				escritor.write(linea);
				escritor.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error al guardar estudiantes: " + e.getMessage());
		}
	}

	/**
	 * Guarda registros en registros.txt.
	 */
	private void guardarRegistrosEnArchivo() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_REGISTROS))) {
			for (RegistroCertificacion r : listaRegistros) {
				String linea = r.obtenerRutEstudiante() + ";" + r.obtenerIdCertificacion() + ";"
						+ r.obtenerFechaRegistro() + ";" + r.obtenerEstadoRegistro() + ";"
						+ r.obtenerPorcentajeProgreso();
				escritor.write(linea);
				escritor.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error al guardar registros: " + e.getMessage());
		}
	}

	/**
	 * Guarda notas en notas.txt.
	 */
	private void guardarNotasEnArchivo() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_NOTAS))) {
			for (Nota n : listaNotas) {
				String linea = n.obtenerRutEstudiante() + ";" + n.obtenerCodigoAsignatura() + ";"
						+ n.obtenerCalificacion() + ";" + n.obtenerEstadoAsignatura() + ";"
						+ n.obtenerSemestreCursado();
				escritor.write(linea);
				escritor.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error al guardar notas: " + e.getMessage());
		}
	}

	/**
	 * Guarda certificaciones en certificaciones.txt.
	 */
	private void guardarCertificacionesEnArchivo() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_CERTIFICACIONES))) {
			for (Certificacion c : mapaCertificaciones.values()) {
				String linea = c.obtenerIdCertificacion() + ";" + c.obtenerNombreCertificacion() + ";"
						+ c.obtenerDescripcionCertificacion() + ";" + c.obtenerCreditosRequeridos() + ";"
						+ c.obtenerAniosValidez();
				escritor.write(linea);
				escritor.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error al guardar certificaciones: " + e.getMessage());
		}
	}

	// ---------------------------------------------------------
	// CONSULTAS Y LOGICA
	// ---------------------------------------------------------

	/** @return estudiantes cargados */
	public Collection<Estudiante> obtenerEstudiantes() {
		return mapaEstudiantes.values();
	}

	/** @return cursos cargados */
	public Collection<Curso> obtenerCursos() {
		return mapaCursos.values();
	}

	/** @return certificaciones cargadas */
	public Collection<Certificacion> obtenerCertificaciones() {
		return mapaCertificaciones.values();
	}

	/** @return registros cargados */
	public List<RegistroCertificacion> obtenerRegistros() {
		return listaRegistros;
	}

	/** @return notas cargadas */
	public List<Nota> obtenerNotas() {
		return listaNotas;
	}

	/** @return relaciones certificacion-curso cargadas */
	public List<AsignaturaCertificacion> obtenerAsignaturasCertificacion() {
		return listaAsignaturasCertificacion;
	}

	/** Busca estudiante por rut. */
	public Estudiante buscarEstudiantePorRut(String rut) {
		return mapaEstudiantes.get(rut);
	}

	/** Retorna notas por rut. */
	public List<Nota> obtenerNotasPorRut(String rut) {
		List<Nota> resultado = new ArrayList<>();
		for (Nota n : listaNotas) {
			if (n.obtenerRutEstudiante().equals(rut)) {
				resultado.add(n);
			}
		}
		return resultado;
	}

	/** Retorna registros por rut. */
	public List<RegistroCertificacion> obtenerRegistrosPorRut(String rut) {
		List<RegistroCertificacion> resultado = new ArrayList<>();
		for (RegistroCertificacion r : listaRegistros) {
			if (r.obtenerRutEstudiante().equals(rut)) {
				resultado.add(r);
			}
		}
		return resultado;
	}

	/** Retorna cursos asociados a una certificacion. */
	public List<Curso> obtenerCursosParaCertificacion(String idCertificacion) {
		List<Curso> resultado = new ArrayList<>();
		for (AsignaturaCertificacion ac : listaAsignaturasCertificacion) {
			if (ac.obtenerIdCertificacion().equals(idCertificacion)) {
				Curso c = mapaCursos.get(ac.obtenerNrcCurso());
				if (c != null) {
					resultado.add(c);
				}
			}
		}
		return resultado;
	}

	/** Busca certificacion por id. */
	public Certificacion buscarCertificacionPorId(String idCertificacion) {
		return mapaCertificaciones.get(idCertificacion);
	}

	/** Modifica una certificacion y guarda cambios. */
	public void modificarCertificacion(String idCertificacion, String nuevoNombre, String nuevaDescripcion,
			int nuevosCreditos, int nuevaValidez) {

		Certificacion c = mapaCertificaciones.get(idCertificacion);
		if (c == null) {
			return;
		}

		c.asignarNombreCertificacion(nuevoNombre);
		c.asignarDescripcionCertificacion(nuevaDescripcion);
		c.asignarCreditosRequeridos(nuevosCreditos);
		c.asignarAniosValidez(nuevaValidez);

		guardarCertificacionesEnArchivo();
	}

	/**
	 * Inscribe estudiante en certificacion si cumple creditos aprobados.
	 */
	public boolean inscribirEstudianteEnCertificacion(String rut, String idCertificacion) {
		Estudiante est = mapaEstudiantes.get(rut);
		Certificacion cert = mapaCertificaciones.get(idCertificacion);
		if (est == null || cert == null) {
			return false;
		}

		int creditosAprobados = 0;
		for (Nota n : listaNotas) {
			if (n.obtenerRutEstudiante().equals(rut) && n.obtenerEstadoAsignatura().equalsIgnoreCase("Aprobada")) {
				Curso c = mapaCursos.get(n.obtenerCodigoAsignatura());
				if (c != null) {
					creditosAprobados += c.obtenerCreditos();
				}
			}
		}

		if (creditosAprobados < cert.obtenerCreditosRequeridos()) {
			return false;
		}

		String fechaHoy = String.valueOf(System.currentTimeMillis());
		RegistroCertificacion registro = new RegistroCertificacion(rut, idCertificacion, fechaHoy, "Activa", 0.0);

		listaRegistros.add(registro);
		est.agregarRegistroCertificacion(registro);
		guardarRegistrosEnArchivo();
		return true;
	}

	/**
	 * Verifica si el estudiante aprobo todos los cursos asociados a la
	 * certificacion.
	 */
	private boolean haCompletadoCertificacion(String rut, String idCertificacion) {

		List<String> cursosNecesarios = new ArrayList<>();
		for (AsignaturaCertificacion ac : listaAsignaturasCertificacion) {
			if (ac.obtenerIdCertificacion().equals(idCertificacion)) {
				cursosNecesarios.add(ac.obtenerNrcCurso());
			}
		}

		if (cursosNecesarios.isEmpty()) {
			return false;
		}

		for (String nrc : cursosNecesarios) {
			boolean aprobado = false;

			for (Nota n : listaNotas) {
				if (n.obtenerRutEstudiante().equals(rut) && n.obtenerCodigoAsignatura().equals(nrc)
						&& n.obtenerEstadoAsignatura().equalsIgnoreCase("Aprobada")) {
					aprobado = true;
					break;
				}
			}

			if (!aprobado) {
				return false;
			}
		}

		return true;
	}

	/** Devuelve estudiantes que completaron una certificacion. */
	public List<Estudiante> obtenerEstudiantesQueCompletaronCertificacion(String idCertificacion) {
		List<Estudiante> lista = new ArrayList<>();
		for (Estudiante e : mapaEstudiantes.values()) {
			if (haCompletadoCertificacion(e.obtenerRut(), idCertificacion)) {
				lista.add(e);
			}
		}
		return lista;
	}

	/**
	 * Genera un archivo de certificados para estudiantes que completaron una
	 * certificacion.
	 *
	 * @return nombre del archivo generado o null si no existe la certificacion
	 */
	public String generarArchivoCertificados(String idCertificacion) {
		Certificacion cert = mapaCertificaciones.get(idCertificacion);
		if (cert == null) {
			return null;
		}

		List<Estudiante> lista = obtenerEstudiantesQueCompletaronCertificacion(idCertificacion);
		String nombreArchivo = "certificados_" + idCertificacion + ".txt";

		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
			for (Estudiante e : lista) {
				String linea = "Se certifica que " + e.obtenerNombreCompleto() + " (RUT: " + e.obtenerRut()
						+ ") ha completado la certificacion '" + cert.obtenerNombreCertificacion() + "'.";
				escritor.write(linea);
				escritor.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error al generar certificados: " + e.getMessage());
		}

		return nombreArchivo;
	}

	/** Calcula inscripciones por certificacion. */
	public Map<String, Integer> calcularInscripcionesPorCertificacion() {
		Map<String, Integer> mapa = new LinkedHashMap<>();
		for (RegistroCertificacion r : listaRegistros) {
			String id = r.obtenerIdCertificacion();
			mapa.put(id, mapa.getOrDefault(id, 0) + 1);
		}
		return mapa;
	}

	/** Calcula reprobaciones por curso. */
	public Map<String, Integer> calcularReprobacionesPorCurso() {
		Map<String, Integer> mapa = new LinkedHashMap<>();
		for (Nota n : listaNotas) {
			if (n.obtenerEstadoAsignatura().equalsIgnoreCase("Reprobada")) {
				String codigo = n.obtenerCodigoAsignatura();
				mapa.put(codigo, mapa.getOrDefault(codigo, 0) + 1);
			}
		}
		return mapa;
	}
}

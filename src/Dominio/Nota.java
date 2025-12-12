// NOMBRE COMPLETO - RUT - CARRERA
package Dominio;

/**
 * Representa una nota o resultado academico de un estudiante en una asignatura.
 * Se usa para calcular creditos aprobados y reprobaciones.
 */
public class Nota {

    /** Rut del estudiante. */
    private String rutEstudiante;
    /** Codigo o NRC de la asignatura. */
    private String codigoAsignatura;
    /** Calificacion obtenida. */
    private double calificacion;
    /** Estado de la asignatura (Aprobada, Reprobada, etc.). */
    private String estadoAsignatura;
    /** Semestre en el que se curso (texto segun archivo). */
    private String semestreCursado;

    /**
     * Crea una nota con sus datos.
     *
     * @param rutEstudiante rut del estudiante
     * @param codigoAsignatura codigo o nrc
     * @param calificacion calificacion
     * @param estadoAsignatura estado (Aprobada/Reprobada)
     * @param semestreCursado semestre cursado
     */
    public Nota(String rutEstudiante, String codigoAsignatura, double calificacion, String estadoAsignatura, String semestreCursado) {
        this.rutEstudiante = rutEstudiante;
        this.codigoAsignatura = codigoAsignatura;
        this.calificacion = calificacion;
        this.estadoAsignatura = estadoAsignatura;
        this.semestreCursado = semestreCursado;
    }

    /** @return rut del estudiante */
    public String obtenerRutEstudiante() {
        return rutEstudiante;
    }

    /** @return codigo o nrc de asignatura */
    public String obtenerCodigoAsignatura() {
        return codigoAsignatura;
    }

    /** @return calificacion */
    public double obtenerCalificacion() {
        return calificacion;
    }

    /** @return estado de la asignatura */
    public String obtenerEstadoAsignatura() {
        return estadoAsignatura;
    }

    /** @return semestre cursado */
    public String obtenerSemestreCursado() {
        return semestreCursado;
    }
}

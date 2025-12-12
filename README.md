# Taller 4 POO

Taller 4 de Programacion Orientada a Objetos.

---

## Autor
robertchirino
21.370.498-2

---

## Descripcion

Aplicacion desarrollada en Java que permite la gestion academica de estudiantes, cursos y lineas de certificacion.  
El sistema cuenta con autenticacion por rol (Administrador, Coordinador y Estudiante), persistencia en archivos de texto y validaciones academicas.

---

## Estructura del proyecto

### Paquete Dominio

Contiene las clases del modelo:
- Usuario
- Administrador
- Coordinador
- Estudiante
- Curso
- Nota
- RegistroCertificacion
- Certificacion
- CertificacionTecnica
- CertificacionAvanzada
- AsignaturaCertificacion
- VisitanteCertificacion

### Paquete logica

Contiene la logica del sistema:
- GestorDatos
- FabricaUsuario
- EstrategiaCalculoPromedio
- EstrategiaPromedioGeneral
- EstrategiaPromedioPorSemestre

### Paquete GUI

Contiene la interfaz grafica:
- Main
- VentanaInicioSesion
- VentanaAdministrador
- VentanaCoordinador
- VentanaEstudiante
- VentanaFxIntegrada

---

## Patrones de diseño implementados

- **Singleton:** GestorDatos
- **Factory:** FabricaUsuario
- **Strategy:** calculo de promedios
- **Visitor:** operaciones segun tipo de certificacion

---

## Archivos de Datos

El sistema utiliza archivos de texto para almacenar informacion:

- usuarios.txt  
- estudiantes.txt  
- cursos.txt  
- certificaciones.txt  
- registros.txt  
- notas.txt  
- asignaturas_certificaciones.txt  

Estos archivos deben estar en la raiz del proyecto.

---

## Instrucciones de ejecucion

1. Clonar el repositorio desde GitHub.
2. Importar el proyecto en Eclipse como **Existing Java Project**.
3. Verificar que el proyecto use **JDK 23**.
4. Verificar que los archivos `.txt` esten en la raiz del proyecto.
5. Ejecutar la clase **Main**.

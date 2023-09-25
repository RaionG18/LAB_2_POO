// Importando las clases necesarias
import java.util.Arrays;
import java.util.Scanner;

// Clase que representa un laboratorio en una universidad
class LaboratorioUniversidad {
    // Matriz que representa el horario del laboratorio con los cursos asignados
    private Curso[][] horarioLab = new Curso[7][14];
    // Capacidad máxima del laboratorio
    private int capacidadMaxima;

    // Constructor del laboratorio
    public LaboratorioUniversidad(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    // Método para asignar un curso al horario del laboratorio
    public String asignarCurso(Curso curso, int dia, int hora) {
        // Verifica si el horario está disponible
        if (horarioLab[dia][hora] == null) {
            // Verifica si el número de estudiantes del curso no excede la capacidad máxima
            if (curso.getEstudiantes() <= capacidadMaxima) {
                horarioLab[dia][hora] = curso;
                return "Curso asignado exitosamente.";
            } else if (curso.getEstudiantes() <= 2 * capacidadMaxima) {
                return "Advertencia: Estudiantes exceden la capacidad máxima pero están dentro del límite permitido.";
            } else {
                return "Error: Estudiantes exceden el doble de la capacidad máxima.";
            }
        } else {
            return "Horario ocupado.";
        }
    }

    // Método para ver el curso asignado en un horario específico
    public Curso verCurso(int dia, int hora) {
        return horarioLab[dia][hora];
    }

    // Método para eliminar un curso del horario
    public String eliminarCurso(int dia, int hora) {
        horarioLab[dia][hora] = null;
        return "Curso eliminado.";
    }

    // Método para verificar si un horario está disponible
    public boolean esLabDisponible(int dia, int hora) {
        return horarioLab[dia][hora] == null;
    }

    // Método para reiniciar el horario del laboratorio
    public String reiniciarHorario() {
        horarioLab = new Curso[7][14];
        return "Horario reiniciado para el nuevo semestre.";
    }

    // Método para mostrar el profesor de un curso en un horario específico
    public Profesor mostrarProfesor(int dia, int hora) {
        Curso curso = horarioLab[dia][hora];
        if (curso != null) {
            return curso.getProfesor();
        } else {
            return null;
        }
    }
}

// Clase que representa un curso
class Curso {
    // Atributos del curso
    private String codigo;
    private String nombre;
    private int periodos;
    private String[] dias;
    private String hora;
    private int estudiantes;
    private Profesor profesor;

    // Constructor del curso
    public Curso(String codigo, String nombre, int periodos, String[] dias, String hora, int estudiantes, Profesor profesor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.periodos = periodos;
        this.dias = dias;
        this.hora = hora;
        this.estudiantes = estudiantes;
        this.profesor = profesor;
    }

    // Método para obtener el número de estudiantes del curso
    public int getEstudiantes() {
        return estudiantes;
    }

    // Método para obtener el profesor del curso
    public Profesor getProfesor() {
        return profesor;
    }

    // Método para representar el curso como una cadena de texto
    @Override
    public String toString() {
        return "Curso: " + nombre + "\nProfesor: " + profesor.getNombre();
    }
}

// Clase que representa un profesor
class Profesor {
    // Atributos del profesor
    private String nombre;
    private String carnet;
    private String correo;
    private String telefono;

    // Constructor del profesor
    public Profesor(String nombre, String carnet, String correo, String telefono) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Método para obtener el nombre del profesor
    public String getNombre() {
        return nombre;
    }

    // Método para representar al profesor como una cadena de texto
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nCorreo: " + correo;
    }
}

// Clase Main
public class Main {
    public static void main(String[] args) {
        // Creando un objeto Scanner para recibir la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        // Creando un objeto LaboratorioUniversidad con capacidad máxima de 30
        LaboratorioUniversidad lab = new LaboratorioUniversidad(30);

        // Bucle infinito para mostrar el menú al usuario
        while (true) {
            // Mostrando el menú
            System.out.println("\n--- Menú ---");
            System.out.println("1. Asignar curso");
            System.out.println("2. Ver curso");
            System.out.println("3. Eliminar curso");
            System.out.println("4. Mostrar profesor");
            System.out.println("5. Reiniciar horario");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            // Recibiendo la opción del usuario
            int opcion = scanner.nextInt();

            // Evaluando la opción del usuario
            switch (opcion) {
                // Caso para asignar un curso
                case 1:
                    // Solicitando datos del curso y del profesor al usuario
                    System.out.print("Ingrese código del curso: ");
                    String codigo = scanner.next();
                    System.out.print("Ingrese nombre del curso: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese duración del curso: ");
                    int periodos = scanner.nextInt();
                    System.out.print("Ingrese días del curso (separados por comas): ");
                    String[] dias = scanner.next().split(",");
                    System.out.print("Ingrese hora del curso: ");
                    String hora = scanner.next();
                    System.out.print("Ingrese número de estudiantes: ");
                    int estudiantes = scanner.nextInt();
                    System.out.print("Ingrese nombre del profesor: ");
                    String nombreProfesor = scanner.next();
                    System.out.print("Ingrese carnet del profesor: ");
                    String carnet = scanner.next();
                    System.out.print("Ingrese correo del profesor: ");
                    String correo = scanner.next();
                    System.out.print("Ingrese teléfono del profesor: ");
                    String telefono = scanner.next();

                    Profesor profesor = new Profesor(nombreProfesor, carnet, correo, telefono);
                    Curso curso = new Curso(codigo, nombre, periodos, dias, hora, estudiantes, profesor);

                    System.out.print("Ingrese día (0-6): ");
                    int dia = scanner.nextInt();
                    System.out.print("Ingrese hora (0-13): ");
                    int horaCurso = scanner.nextInt();

                    String mensaje = lab.asignarCurso(curso, dia, horaCurso);
                    System.out.println(mensaje);
                    break;

                // Caso para ver un curso
                case 2:
                    System.out.print("Ingrese día (0-6): ");
                    dia = scanner.nextInt();
                    System.out.print("Ingrese hora (0-13): ");
                    horaCurso = scanner.nextInt();

                    Curso detallesCurso = lab.verCurso(dia, horaCurso);
                    if (detallesCurso != null) {
                        System.out.println(detallesCurso);
                    } else {
                        System.out.println("No hay curso asignado en ese horario.");
                    }
                    break;

                // Caso para eliminar un curso
                case 3:
                    System.out.print("Ingrese día (0-6): ");
                    dia = scanner.nextInt();
                    System.out.print("Ingrese hora (0-13): ");
                    horaCurso = scanner.nextInt();

                    mensaje = lab.eliminarCurso(dia, horaCurso);
                    System.out.println(mensaje);
                    break;

                // Caso para mostrar el profesor de un curso
                case 4:
                    System.out.print("Ingrese día (0-6): ");
                    dia = scanner.nextInt();
                    System.out.print("Ingrese hora (0-13): ");
                    horaCurso = scanner.nextInt();

                    Profesor profesorDetalle = lab.mostrarProfesor(dia, horaCurso);
                    if (profesorDetalle != null) {
                        System.out.println(profesorDetalle);
                    } else {
                        System.out.println("No hay profesor asignado en ese horario.");
                    }
                    break;

                // Caso para reiniciar el horario
                case 5:
                    mensaje = lab.reiniciarHorario();
                    System.out.println(mensaje);
                    break;

                // Caso para salir del programa
                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                // Caso para opciones no válidas
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}

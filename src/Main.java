import java.util.Arrays;
import java.util.Scanner;

class LaboratorioUniversidad {
    private Curso[][] horarioLab = new Curso[7][14];
    private int capacidadMaxima;

    public LaboratorioUniversidad(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public String asignarCurso(Curso curso, int dia, int hora) {
        if (horarioLab[dia][hora] == null) {
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

    public Curso verCurso(int dia, int hora) {
        return horarioLab[dia][hora];
    }

    public String eliminarCurso(int dia, int hora) {
        horarioLab[dia][hora] = null;
        return "Curso eliminado.";
    }

    public boolean esLabDisponible(int dia, int hora) {
        return horarioLab[dia][hora] == null;
    }

    public String reiniciarHorario() {
        horarioLab = new Curso[7][14];
        return "Horario reiniciado para el nuevo semestre.";
    }

    public Profesor mostrarProfesor(int dia, int hora) {
        Curso curso = horarioLab[dia][hora];
        if (curso != null) {
            return curso.getProfesor();
        } else {
            return null;
        }
    }
}

class Curso {
    private String codigo;
    private String nombre;
    private int periodos;
    private String[] dias;
    private String hora;
    private int estudiantes;
    private Profesor profesor;

    public Curso(String codigo, String nombre, int periodos, String[] dias, String hora, int estudiantes, Profesor profesor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.periodos = periodos;
        this.dias = dias;
        this.hora = hora;
        this.estudiantes = estudiantes;
        this.profesor = profesor;
    }

    public int getEstudiantes() {
        return estudiantes;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    @Override
    public String toString() {
        return "Curso: " + nombre + "\nProfesor: " + profesor.getNombre();
    }
}

class Profesor {
    private String nombre;
    private String carnet;
    private String correo;
    private String telefono;

    public Profesor(String nombre, String carnet, String correo, String telefono) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nCorreo: " + correo;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LaboratorioUniversidad lab = new LaboratorioUniversidad(30);

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Asignar curso");
            System.out.println("2. Ver curso");
            System.out.println("3. Eliminar curso");
            System.out.println("4. Mostrar profesor");
            System.out.println("5. Reiniciar horario");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
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

                case 3:
                    System.out.print("Ingrese día (0-6): ");
                    dia = scanner.nextInt();
                    System.out.print("Ingrese hora (0-13): ");
                    horaCurso = scanner.nextInt();

                    mensaje = lab.eliminarCurso(dia, horaCurso);
                    System.out.println(mensaje);
                    break;

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

                case 5:
                    mensaje = lab.reiniciarHorario();
                    System.out.println(mensaje);
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}

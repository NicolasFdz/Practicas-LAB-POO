package JavaPracticas.Practica2;

public class Main {
    public static void main(String[] args) {
        // Crear estudiantes
        EstudianteNF e1 = new EstudianteNF("Nicolás Fernández", "2099698", 18, "Ciencias Computacionales", 5);
        EstudianteNF e2 = new EstudianteNF("Ana López", "2099546", 20, "Física", 3);
        EstudianteNF e3 = new EstudianteNF("Luis Pérez", "2099634", 22, "Ciencias Computacionales", 6);
        EstudianteNF e4 = new EstudianteNF("María Torres", "2099236", 19, "Actuaria", 2);
        EstudianteNF e5 = new EstudianteNF("Carlos Ruiz", "2099932", 23, "Matematicas", 7);

        // Crear universidad
        Universidad9698 uni = new Universidad9698(10);

        // Agregar estudiantes
        uni.agregarEstudiante(e1);
        uni.agregarEstudiante(e2);
        uni.agregarEstudiante(e3);
        uni.agregarEstudiante(e4);
        uni.agregarEstudiante(e5);

        // Mostrar todos
        uni.mostrarTodos();

        // Buscar uno
        EstudianteNF buscado = uni.buscarPorMatricula("2099698");
        if (buscado != null) {
            System.out.println("Estudiante encontrado:");
            buscado.mostrarDatos();
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
}

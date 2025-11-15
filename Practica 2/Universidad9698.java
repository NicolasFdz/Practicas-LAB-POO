package JavaPracticas.Practica2;

public class Universidad9698 {
    EstudianteNF[] estudiantes;
    int contador;

    public Universidad9698(int capacidad) {
        estudiantes = new EstudianteNF[capacidad];
        contador = 0;
    }

    public void agregarEstudiante(EstudianteNF e) {
        if (contador < estudiantes.length) {
            estudiantes[contador] = e;
            contador++;
        } else {
            System.out.println("No hay espacio para más estudiantes.");
        }
    }

    public EstudianteNF buscarPorMatricula(String matricula) {
        for (int i = 0; i < contador; i++) {
            if (estudiantes[i].matricula.equals(matricula)) {
                return estudiantes[i];
            }
        }
        return null;
    }

    public void mostrarTodos() {
        for (int i = 0; i < contador; i++) {
            estudiantes[i].mostrarDatos();
            System.out.println("-----");
        }
    }
}

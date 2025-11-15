package JavaPracticas.Practica2;

public class EstudianteNF {
    // Atributos
    String nombre;
    String matricula;
    int edad;
    String carrera;
    int semestreActual;

    // Constructor vacío
    public EstudianteNF() {
        nombre = "";
        matricula = "";
        edad = 0;
        carrera = "";
        semestreActual = 0;
    }

    // Constructor con nombre y matrícula
    public EstudianteNF(String nombre, String matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
        edad = 0;
        carrera = "";
        semestreActual = 0;
    }

    // Constructor completo
    public EstudianteNF(String nombre, String matricula, int edad, String carrera, int semestreActual) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.edad = edad;
        this.carrera = carrera;
        this.semestreActual = semestreActual;
    }

    // Métodos
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Edad: " + edad);
        System.out.println("Carrera: " + carrera);
        System.out.println("Semestre actual: " + semestreActual);
    }

    public void avanzarSemestre() {
        semestreActual++;
    }

    public void cambiarCarrera(String nuevaCarrera) {
        carrera = nuevaCarrera;
    }

    public void actualizarEdad(int nuevaEdad) {
        edad = nuevaEdad;
    }

    public boolean esMayorDeEdad() {
        return edad >= 18;
    }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestorEstudiantes {

    // Calcula la nota media de un estudiante
    public static double calcularNotaMedia(Estudiante estudiante) {
        double suma = 0;
        for (int i = 0; i < estudiante.getNotas().length; i++) { // Error: índice fuera de rango  // Solo era necesario quitar el igual del bucle haciendo que se salga de rango.
            suma += estudiante.getNotas()[i];
        }
        if (estudiante.getNotas() != null){
            return suma / 3 ;    // Simplemente hemos añadido una condicion que hace que los estudiantes con notas se les divida siempre entre 3 "Numero total de pruebas" y los estudiantes sin pruebas sin realizar tendran un 0.
        } else return 0;
    }

    // Encuentra al estudiante con la mejor nota media
    public static Estudiante encontrarMejorEstudiante(Estudiante[] estudiantes) {
        Estudiante mejor = null;
        double mejorNota = -1;

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNotas() == null){   // Si el estudiante no tiene notas que devuelva NULL (Evita el error)
                return null;
            }
            double media = calcularNotaMedia(estudiante); // Posible fallo aquí
            if (media > mejorNota) {
                mejorNota = media;
                mejor = estudiante;
            }
        }
        return mejor; // Error si la lista está vacía
    }

    // Guarda los resultados en un fichero
    public static void guardarResultados(Estudiante[] estudiantes, String rutaFichero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaFichero))) {
            for (Estudiante estudiante : estudiantes) {
                writer.write("Nombre: " + estudiante.getNombre() + ", Nota Media: " +
                        calcularNotaMedia(estudiante)); // Posible fallo si calcularNotaMedia lanza una excepción
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero: " + e.getMessage());
        }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    // prestamo = [idPrestamo, nombreUsuario, tituloLibro, diasPrestamo, multaPorDia]
    static ArrayList<ArrayList<Object>> prestamos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarPrestamo();
                case 2 -> mostrarPrestamos();
                case 3 -> buscarPrestamoPorId();
                case 4 -> actualizarPrestamo();
                case 5 -> eliminarPrestamo();
                case 6 -> calcularTotalMultas();
                case 7 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
            System.out.println();
        } while (opcion != 7);

        sc.close();
    }

    static void mostrarMenu() {
        System.out.println("=== Biblioteca: Gestión de Préstamos ===");
        System.out.println("1. Registrar nuevo préstamo");
        System.out.println("2. Mostrar todos los préstamos");
        System.out.println("3. Buscar préstamo por ID");
        System.out.println("4. Actualizar un préstamo");
        System.out.println("5. Eliminar un préstamo");
        System.out.println("6. Calcular total de multas");
        System.out.println("7. Salir");
    }

    // ====== CRUD (por implementar) ======
   

    static void buscarPrestamoPorId() { /* TODO */ }
    static void actualizarPrestamo() { /* TODO */ }
    static void eliminarPrestamo() { /* TODO */ }

    // ====== Cálculo (por implementar) ======
    static void calcularTotalMultas() { /* TODO */ }

    // ====== Utilidades mínimas ======
    static int leerEntero(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Ingrese un entero válido.");
            }
        }
    }

    static String leerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }
    
// opcion 1
static void registrarPrestamo() {
    int id = leerEntero("ID del préstamo: ");
    String usuario = leerTexto("Nombre del usuario: ");
    String libro = leerTexto("Título del libro: ");
    int dias = leerEntero("Días de préstamo: ");

    System.out.print("Multa por día: ");
    double multa = Double.parseDouble(sc.nextLine());

    ArrayList<Object> prestamo = new ArrayList<>();
    prestamo.add(id);
    prestamo.add(usuario);
    prestamo.add(libro);
    prestamo.add(dias);
    prestamo.add(multa);

    prestamos.add(prestamo);

    System.out.println("Préstamo registrado correctamente.");
}


// opcion 2
static void mostrarPrestamos() {
    if (prestamos.isEmpty()) {
        System.out.println("No hay préstamos registrados.");
        return;
    }

    System.out.println("=== Lista de Préstamos ===");
    for (ArrayList<Object> prestamo : prestamos) {
        System.out.println(
            "ID: " + prestamo.get(0)
            + " | Usuario: " + prestamo.get(1)
            + " | Libro: " + prestamo.get(2)
            + " | Días: " + prestamo.get(3)
            + " | Multa/día: $" + prestamo.get(4)
        );
    }
}
}
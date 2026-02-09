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

    // ====== OPCIÓN 1 ======
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

    // ====== OPCIÓN 2 ======
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

    // ====== OPCIÓN 3 ======
    static void buscarPrestamoPorId() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        int id = leerEntero("Ingrese el ID del préstamo a buscar: ");
        boolean encontrado = false;

        for (ArrayList<Object> prestamo : prestamos) {
            if ((int) prestamo.get(0) == id) {
                System.out.println("=== Préstamo encontrado ===");
                System.out.println("ID: " + prestamo.get(0));
                System.out.println("Usuario: " + prestamo.get(1));
                System.out.println("Libro: " + prestamo.get(2));
                System.out.println("Días de préstamo: " + prestamo.get(3));
                System.out.println("Multa por día: $" + prestamo.get(4));
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró un préstamo con ese ID.");
        }
    }

    // ====== OPCIÓN 4 ======
    static void actualizarPrestamo() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        int id = leerEntero("Ingrese el ID del préstamo a actualizar: ");
        ArrayList<Object> prestamoEncontrado = null;

        for (ArrayList<Object> prestamo : prestamos) {
            if ((int) prestamo.get(0) == id) {
                prestamoEncontrado = prestamo;
                break;
            }
        }

        if (prestamoEncontrado == null) {
            System.out.println("No se encontró un préstamo con ese ID.");
            return;
        }

        System.out.println("=== Préstamo actual ===");
        System.out.println("1. Usuario: " + prestamoEncontrado.get(1));
        System.out.println("2. Libro: " + prestamoEncontrado.get(2));
        System.out.println("3. Días de préstamo: " + prestamoEncontrado.get(3));
        System.out.println("4. Multa por día: $" + prestamoEncontrado.get(4));

        int opcion = leerEntero("Seleccione el campo a modificar (1-4): ");

        switch (opcion) {
            case 1 -> prestamoEncontrado.set(1, leerTexto("Nuevo nombre de usuario: "));
            case 2 -> prestamoEncontrado.set(2, leerTexto("Nuevo título del libro: "));
            case 3 -> prestamoEncontrado.set(3, leerEntero("Nuevos días de préstamo: "));
            case 4 -> {
                System.out.print("Nueva multa por día: ");
                prestamoEncontrado.set(4, Double.parseDouble(sc.nextLine()));
            }
            default -> {
                System.out.println("Opción inválida.");
                return;
            }
        }

        System.out.println("Préstamo actualizado correctamente.");
    }

    // ====== OPCIÓN 5 ======
    static void eliminarPrestamo() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        int id = leerEntero("Ingrese el ID del préstamo a eliminar: ");
        boolean eliminado = false;

        for (int i = 0; i < prestamos.size(); i++) {
            if ((int) prestamos.get(i).get(0) == id) {
                prestamos.remove(i);
                eliminado = true;
                System.out.println("Préstamo eliminado correctamente.");
                break;
            }
        }

        if (!eliminado) {
            System.out.println("No se encontró un préstamo con ese ID.");
        }
    }

    // ====== OPCIÓN 6 ======
    static void calcularTotalMultas() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        double total = 0;

        for (ArrayList<Object> prestamo : prestamos) {
            int dias = (int) prestamo.get(3);
            double multa = (double) prestamo.get(4);
            total += dias * multa;
        }

        System.out.println("Total acumulado de multas: $" + total);
    }

    // ====== UTILIDADES ======
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
}

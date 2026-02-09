//OPCIÓN 3: Buscar prestamo por id
static void Validaciones() {
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


//OPCIÓN 4: Actualizar un préstamo
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


package tema1.agendaTelefonica;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        /*
         * Bucle que se ejecuta hasta que pulsamos 6
         * Menu sencillo y leemos la opcion introducida
         */
        do {
            System.out.println("\n--- Menú Agenda ---");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Modificar Contacto");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            /*
             * Depende de la opcion pulsada llamamos a los metodos mencionados
             */
            switch (opcion) {
                case 1:
                    try {
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    int telefono = scanner.nextInt();
                    agenda.agregarContacto(new Contacto(nombre, telefono));
                    } catch (InputMismatchException e) {
                        System.out.println("Error al agregar contacto");
                        return;
                    }
                    break;
                case 2:
                    agenda.mostrarContactos();
                    break;
                case 3:
                    System.out.print("Nombre a buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    Contacto contacto = agenda.buscarContacto(nombreBuscar);
                    if (contacto != null) {
                        System.out.println(contacto);
                    } else {
                        System.out.println("Contacto no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Nombre a Modificar: ");
                    String nombreModificar = scanner.nextLine();
                    agenda.modificarContacto(nombreModificar);
                    break;
                case 5:
                    System.out.print("Nombre a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    agenda.eliminarContacto(nombreEliminar);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }
}

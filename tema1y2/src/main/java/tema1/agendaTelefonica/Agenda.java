package tema1.agendaTelefonica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
    private ArrayList<Contacto> contactos;
    private final String ARCHIVO = "AccesoADatos.obj";
    private final Scanner lector = new Scanner(System.in);

    public Agenda() {
        contactos = new ArrayList<>();
        cargarAgenda();
    }

    /**
     * @param contacto es el parametro que añadimos a nuestra Agenda(ArrayList)
     */
    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
        guardarAgenda();
    }

    /**
     * Comprobamos si está la Agenda vacia y si hay algún objeto lo mostramos
     */
    public void mostrarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
        } else {
            for (Contacto contacto : contactos) {
                System.out.println(contacto);
            }
        }
    }

    /**
     * @param nombre parametro que utilizamos para comparar si está en nuestra
     *               Agenda
     * @return el contacto encontrado, si no esta devolvemos un null
     */
    public Contacto buscarContacto(String nombre) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equals(nombre)) {
                return contacto;
            }
        }
        return null;
    }

    /**
     * Buscamos el nombre, comprobamos que no sea nulo, y lo borramos
     *
     * @param nombre parametro que utilizamos para buscar el contacto
     */
    public void eliminarContacto(String nombre) {
        Contacto contacto = buscarContacto(nombre);
        if (contacto != null) {
            contactos.remove(contacto);
            guardarAgenda();
            System.out.println("Contacto eliminado.");
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    /**
     * Comprobamos que no sea nulo y leemos el nombre y telefono nuevo para cambiarlo con un set
     * Si no encontramos el contacto mostramos un mensaje
     *
     * @param nombre parametro para buscar el contacto
     */
    public void modificarContacto(String nombre) {
        Contacto contacto = buscarContacto(nombre);
        if (contacto != null) {
            System.out.println("Contacto encontrado: " + contacto);

            System.out.println("Introduce el nuevo nombre: ");
            contacto.setNombre(lector.nextLine());

            System.out.println("Introduce el nuevo teléfono: ");
            contacto.setTelefono(lector.nextInt());

            guardarAgenda();
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    /**
     * Aseguramos el cierre con un try with resource
     * Escribe objetos en la ruta definida
     */
    private void guardarAgenda() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(contactos);
        } catch (IOException e) {
            System.out.println("Error guardando la agenda: " + e.getMessage());
        }
    }

    /**
     * Creamos un fichero si no está creado y leemos los objetos que contengan
     */
    private void cargarAgenda() {
        File fichero = new File(ARCHIVO);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            fichero.createNewFile();
            contactos = (ArrayList<Contacto>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error cargando la agenda: " + e.getMessage());
        }
    }
}

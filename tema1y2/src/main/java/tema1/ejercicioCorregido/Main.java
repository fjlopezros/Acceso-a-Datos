package tema1.ejercicioCorregido;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        leerFicheros();
        leerObjeto();
    }

    public static void leerFicheros() {
        try (BufferedReader lector1 = new BufferedReader(new FileReader("persona1.txt"));
             BufferedReader lector2 = new BufferedReader(new FileReader("persona2.txt"));
             ObjectOutputStream escritorObj = new ObjectOutputStream(new FileOutputStream("personas.obj", true))) {

            leerYEscribir(lector1, escritorObj);
            leerYEscribir(lector2, escritorObj);

        } catch (IOException e) {
            System.out.println("Error leerFicheros() " + e.getMessage());
        }
    }

    private static void leerYEscribir(BufferedReader lector, ObjectOutputStream escritorObj) throws IOException {
        String leer;
        while ((leer = lector.readLine()) != null) {
            Persona persona = new Persona();

            persona.setDni(leer);
            persona.setNombre(lector.readLine());
            try {
                int edad = Integer.parseInt(lector.readLine());
                Persona.validarEdad(edad);
                persona.setEdad(edad);
                escritorObj.writeObject(persona);
            } catch (NumberFormatException e) {
                System.out.println("Error de formato en la edad: " + e.getMessage());
            } catch (ValidarEdadException e) {
                System.out.println("Error de validación de edad: " + e.getMessage());
            }
        }
    }

    public static void leerObjeto() {
        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream("personas.obj"))) {
            while (true) {
                try {
                    Persona persona = (Persona) lector.readObject();
                    System.out.println("DNI: " + persona.getDni());
                    System.out.println("Nombre: " + persona.getNombre());
                    System.out.println("Edad: " + persona.getEdad());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leerObjeto() " + e.getMessage());
        }
    }
}

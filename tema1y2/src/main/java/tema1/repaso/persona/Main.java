package tema1.repaso.persona;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        leerFichero();
        leerObjeto();
    }

    public static void leerFichero() {
        try (BufferedReader lector = new BufferedReader(new FileReader("src/main/java/Tema1/repasoExamen/persona1.txt"));
             BufferedReader lector2 = new BufferedReader(new FileReader("src/main/java/Tema1/repasoExamen/persona2.txt"));
             ObjectOutputStream escritorObj = new ObjectOutputStream(new FileOutputStream("src/main/java/Tema1/repasoExamen/personas.obj"))) {

            leerYEscribir(lector, escritorObj);
            leerYEscribir(lector2, escritorObj);

        } catch (IOException e) {
            System.out.println("Error fichero " + e.getMessage());
        }
    }

    public static void leerYEscribir(BufferedReader lector, ObjectOutputStream escritorObj) throws IOException {
        String leer;
        while ((leer = lector.readLine()) != null) {
            Persona persona = new Persona();

            persona.setDni(leer);
            persona.setNombre(lector.readLine());
            try {
                int edad = Integer.parseInt(lector.readLine());
                if (edad < 1 || edad > 120) {
                    throw new ValidarEdadException();
                } else {
                    persona.setEdad(edad);
                }
                escritorObj.writeObject(persona);

            } catch (ValidarEdadException e) {
                System.out.println("Error validarEdad ");
            }
        }
    }

    public static void leerObjeto() {
        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream("src/main/java/Tema1/repasoExamen/personas.obj"))) {
            while (true) {
                try {
                    Persona persona = (Persona) lector.readObject();

                    System.out.println("DNI: " + persona.getDni());
                    System.out.println("NOMBRE: " + persona.getNombre());
                    System.out.println("EDAD: " + persona.getEdad());

                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error leerObjeto() " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}

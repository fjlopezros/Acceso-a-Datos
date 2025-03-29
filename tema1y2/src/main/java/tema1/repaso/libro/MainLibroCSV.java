package tema1.repaso.libro;

import java.io.*;


public class MainLibroCSV {
    public static void main(String[] args) {
        leerYEscribir();
        leerObjetoCSV();
    }

    public static void leerYEscribir() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/Tema1/repasoExamen/libro/Libro1.csv"));
             BufferedReader br2 = new BufferedReader(new FileReader("src/main/java/Tema1/repasoExamen/libro/Libro2.csv"));
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/Tema1/repasoExamen/libro/LibrosCSV.obj"))) {

            leerYEscribirCSV(br, oos);
            leerYEscribirCSV(br2, oos);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void leerYEscribirCSV(BufferedReader br, ObjectOutputStream oos) throws IOException {
        String linea;
        try {
            while ((linea = br.readLine()) != null) {
                String[] split = linea.split(",");
                LibroCSV l = new LibroCSV();
                l.setIsbn(split[0].trim());
                l.setNombre(split[1].trim());
                int paginas = Integer.parseInt(split[2].trim());
                if (paginas > 0) {
                    l.setPaginas(paginas);
                } else {
                    throw new ValidarException("Numero de paginas no valido");
                }
                oos.writeObject(l);
            }
        } catch (ValidarException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void leerObjetoCSV() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/Tema1/repasoExamen/libro/LibrosCSV.obj"))) {
            while (true) {
                try {
                    LibroCSV libro = (LibroCSV) ois.readObject();
                    System.out.println("ISBN: " + libro.getIsbn() + ", Nombre: " + libro.getNombre() + ", Páginas: " + libro.getPaginas());

                } catch (EOFException e) {
                    break;
                }
            }
            // Fin del archivo alcanzado
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}


class LibroCSV implements Serializable {
    private String isbn;
    private String nombre;
    private int paginas;

    public LibroCSV() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
}

class ValidarExceptionCSV extends Exception {
    public ValidarExceptionCSV(String mensaje) {
        super(mensaje);
    }
}



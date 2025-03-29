package tema1.repaso.libro;

import java.io.*;

public class MainLibro {
    public static void main(String[] args) {
        String fichero = "src/main/java/Tema1/repasoExamen/libro/Libro2.txt";
        String fichero2 = "src/main/java/Tema1/repasoExamen/libro/Libros.obj";
        String fichero3 = "src/main/java/Tema1/repasoExamen/libro/Libro1.txt";

        leerYEscribir(fichero, fichero2);
        leerYEscribir(fichero3, fichero2);
        leerObjeto();
    }
    public static void leerYEscribir(String ficheroInicio, String ficheroFinal) {
        try(BufferedReader br = new BufferedReader(new FileReader(ficheroInicio));
            BufferedReader br2 = new BufferedReader(new FileReader(ficheroInicio));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroFinal))){

            leerYEscribir(br, oos);
            leerYEscribir(br2, oos);

        }catch(IOException e){System.out.println(e.getMessage());}
    }
    private static void leerYEscribir(BufferedReader entrada, ObjectOutputStream escribir)throws IOException {
        String linea;
        try {
            while ((linea = entrada.readLine()) != null) {
                Libro l = new Libro();

                l.setIsbn(linea);
                l.setNombre(entrada.readLine());
                int paginas = Integer.parseInt(entrada.readLine());
                if (paginas > 0) {
                    l.setPaginas(paginas);
                } else {
                    throw new ValidarException("Numero de paginas incorrecto");
                }

                entrada.readLine();

                escribir.writeObject(l);
            }
        }catch(ValidarException e){
            System.out.println(e.getMessage());
        }
    }
    public static void leerObjeto(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/Tema1/repasoExamen/libro/Libros.obj"))){
            while(true){
                try{
                    Libro l = (Libro) ois.readObject();

                    System.out.println(l.getIsbn());
                    System.out.println(l.getNombre());
                    System.out.println(l.getPaginas());
                    System.out.println();

                }catch(EOFException e){
                    break;
                }
            }

        }catch(IOException | ClassNotFoundException e){System.out.println(e.getMessage());}
    }
}
class Libro implements Serializable {
    private String isbn;
    private String nombre;
    private int paginas;

    public Libro() {
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
class ValidarException extends Exception{
    public ValidarException(String mensaje) {super(mensaje);}
}

package tema1.repaso.instituto;

import java.io.*;

public class MainInstituto {
    public static void main(String[] args) {
        leerYEscribir();
        leerObjeto();
    }
    public static void leerYEscribir() {
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/java/Tema1/repasoExamen/instituto/Instituto1.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("src/main/java/Tema1/repasoExamen/instituto/Instituto2.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/Tema1/repasoExamen/instituto/Instituto.obj"))){

            leerYEscribir(br, oos);
            leerYEscribir(br2, oos);

        }catch(IOException e){}
    }
    public static void leerYEscribir(BufferedReader br, ObjectOutputStream oos) throws IOException {
        String linea;
        try {
            while ((linea = br.readLine()) != null) {

                Instituto i = new Instituto();

                i.setNombre(linea);

                int capacidad = Integer.parseInt(br.readLine());
                if (capacidad > 0) {
                    i.setCapacidad(capacidad);
                } else {
                    throw new ValidarCapacidad("No valido");
                }

                br.readLine();

                oos.writeObject(i);
            }
        }catch (ValidarCapacidad e) {}
    }
    public static void leerObjeto() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/Tema1/repasoExamen/instituto/Instituto.obj"))){
            while(true){
                try{
                    Instituto i = (Instituto) ois.readObject();

                    System.out.println(i.getNombre() + " " + i.getCapacidad());

                }catch(EOFException e){
                    break;
                }
            }
        }catch(IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}

package tema1.repaso.producto;

import java.io.*;

public class MainProducto {
    public static void main(String[] args) {
        leerFichero();
        leerObjeto();
    }
    public static void leerFichero(){
        try(BufferedReader lector = new BufferedReader(new FileReader("src/main/java/Tema1/repasoExamen/producto/Producto1.txt"));
        BufferedReader lector2 = new BufferedReader(new FileReader("src/main/java/Tema1/repasoExamen/producto/Producto2.txt"));
            ObjectOutputStream escritorObj = new ObjectOutputStream(new FileOutputStream("src/main/java/Tema1/repasoExamen/producto/Productos.obj"))){

            leerYEscribir(lector, escritorObj);
            leerYEscribir(lector2, escritorObj);

        }catch(IOException e){
            System.out.println("Error leerFichero() " + e.getMessage());
        }
    }
    public static void leerYEscribir(BufferedReader lector, ObjectOutputStream escritorObj) throws IOException{
        String leer;
        while((leer = lector.readLine())!=null){
            Producto producto = new Producto();
            try {
                producto.setId(Integer.parseInt(leer));
                String nombre = lector.readLine();
                if(nombre.isBlank()){
                    throw new ProductoInvalidoException("nombre vacio");
                }else{
                    producto.setNombre(nombre);
                }
                double precio = Double.parseDouble(lector.readLine());
                if (precio < 0) {
                    throw new ProductoInvalidoException("precio negativo");
                } else {
                    producto.setPrecio(precio);
                }
                int cantidad = Integer.parseInt(lector.readLine());
                if(cantidad < 0){
                    throw new ProductoInvalidoException("cantidad negativa");
                }else{
                    producto.setCantidad(cantidad);
                }

                escritorObj.writeObject(producto);

            }catch(ProductoInvalidoException e){
                System.out.println("Error producto invalido " + e.getMessage());
            }
        }
    }
    public static void leerObjeto(){
        try(ObjectInputStream lectorObj = new ObjectInputStream(new FileInputStream("src/main/java/Tema1/repasoExamen/producto/Productos.obj"))){
            while(true){
                try{
                    Producto producto = (Producto) lectorObj.readObject();

                    System.out.println("Id: " + producto.getId());
                    System.out.println("Nombre: " + producto.getNombre());
                    System.out.println("Precio: " + producto.getPrecio());
                    System.out.println("Cantidad: " + producto.getCantidad());
                }catch (EOFException e){
                    break;
                }
            }
        }catch(IOException e){
            System.out.println("Error leerObjeto() " + e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("error leerObjeto clase " + e.getMessage());
        }
    }
}

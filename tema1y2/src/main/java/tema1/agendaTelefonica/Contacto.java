package tema1.agendaTelefonica;

import java.io.Serializable;

/***
 * Clase sencilla que contiene dos atributos con dos constructores
 * con sus respectivos getters y setters con un toString 
 * para imprimirlo por pantalla

 * La interfaz Serializable es basicamente para serializar un objeto,
 * lo que significa convertir su estado en un flujo de bytes.
 * @author tarde
 */

public class Contacto implements Serializable{
    private String nombre;
    private int telefono;
    
    public Contacto(String nombre, int telefono){
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    public Contacto(String nombre){
        this(nombre, 0);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", telefono=" + telefono + '}';
    }
    
    
    
}

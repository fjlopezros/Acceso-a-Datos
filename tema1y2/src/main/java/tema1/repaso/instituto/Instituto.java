package tema1.repaso.instituto;

import java.io.Serializable;

public class Instituto implements Serializable {
    private String nombre;
    private int capacidad;

    public Instituto(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}

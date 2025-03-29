package Ejercicio13;

public class Jugadores {
    private Paises pais;
    private String deporte;
    private String ciudad; // Nuevo atributo
    private int edad;      // Nuevo atributo

    // Constructor
    public Jugadores(Paises pais, String deporte, String ciudad, int edad) {
        this.pais = pais;
        this.deporte = deporte;
        this.ciudad = ciudad;
        this.edad = edad;
    }

    // Getters y Setters
    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Jugador de " + pais.toString() + " | Ciudad: " + ciudad + " | Edad: " + edad + " | Deporte: " + deporte;
    }
}

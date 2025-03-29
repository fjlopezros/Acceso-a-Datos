package Ejercicio12;

public class Jugadores {
    private Paises pais;
    private String deporte; // Nuevo atributo

    // Constructor
    public Jugadores(Paises pais, String deporte) {
        this.pais = pais;
        this.deporte = deporte;
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

    //operador ternario (como un if-else)
    @Override
    public String toString() {
        String paisNombre = (pais != null) ? pais.toString() : "Sin país";
        return "Jugador de " + paisNombre + " | Deporte: " + deporte;
    }
}

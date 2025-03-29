package Ejercicio11;

public class Jugadores {
    private Paises pais;

    public Jugadores(Paises pais) {
        this.pais = pais;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Jugador de " + pais.toString();
    }
}

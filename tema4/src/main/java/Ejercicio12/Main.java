package Ejercicio12;

public class Main {
    public static void main(String[] args) {
        // Prueba del método A
        System.out.println("--- Prueba del método A ---");
        GestionJugadoresYPaises.buscarJugadoresPorPaisYDeporte("España", "Fútbol");

        // Prueba del método B
        System.out.println("\n--- Prueba del método B ---");
        GestionJugadoresYPaises.borrarPais("Francia");
    }
}

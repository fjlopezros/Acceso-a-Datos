package tema1.excepciones;


import java.time.Year;
import java.util.Scanner;

public class PruebaExceptiones {
    public static void main(String[] args) {
        try {
            validarEdad(pedirNumero());
            validarPeso(pedirNumero());
            validarAnio(pedirNumero());
        } catch (RangeExcepcion e) {
            System.out.println("Error " + e.getMessage());
        }
        pedirNumero();
    }

    public static int pedirNumero() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un numero: ");
        return sc.nextInt();
    }

    public static void validarEdad(int edad) throws RangeExcepcion {
        if (edad < 0 || edad > 120) {
            throw new RangeExcepcion("Edad fuera de rango logico");
        }
    }

    public static void validarPeso(int peso) throws RangeExcepcion {
        if (peso < 0 || peso > 300) {
            throw new RangeExcepcion("Peso fuera de rango logico");
        }
    }

    public static void validarAnio(int anio) throws RangeExcepcion {
        int anioActual = Year.now().getValue();

        if(anio < 1900 || anio > anioActual) {
            throw new RangeExcepcion("Anio fuera de rango logico");
        }
    }
}

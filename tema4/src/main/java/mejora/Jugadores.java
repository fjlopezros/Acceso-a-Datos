package mejora;

public class Jugadores {

    // Propiedades
    private String nombre;
    private String deporte;
    private int edad;
    private Paises pais;

    // Constructores
    public Jugadores() {
    }

    public Jugadores(String nombre, String deporte, Paises pais, int edad) {
        this.nombre = nombre;
        this.deporte = deporte;
        this.edad = edad;
        this.pais = pais;
    }

    // Métodos Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public Paises getPais() {
        return pais;
    }

}
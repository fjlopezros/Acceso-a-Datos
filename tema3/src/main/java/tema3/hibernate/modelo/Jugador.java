package tema3.hibernate.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "jugadores")
public class Jugador {

    @Id
    @Column(name = "Codigo")
    private int codigo;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Procedencia")
    private String procedencia;

    @Column(name = "Altura_pies")
    private double alturaPies;

    @Column(name = "Peso_libras")
    private double pesoLibras;

    @Column(name = "Posicion")
    private String posicion;

    @ManyToOne
    @JoinColumn(name = "Nombre_equipo")
    private Equipo equipo;

    // Constructor por defecto
    public Jugador() {}

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public double getAlturaPies() {
        return alturaPies;
    }

    public void setAlturaPies(double alturaPies) {
        this.alturaPies = alturaPies;
    }

    public double getPesoLibras() {
        return pesoLibras;
    }

    public void setPesoLibras(double pesoLibras) {
        this.pesoLibras = pesoLibras;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}

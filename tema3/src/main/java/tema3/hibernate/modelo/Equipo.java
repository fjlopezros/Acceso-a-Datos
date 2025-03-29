package tema3.hibernate.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Ciudad")
    private String ciudad;

    @Column(name = "Conferencia")
    private String conferencia;

    @Column(name = "Division")
    private String division;

    // Constructor por defecto
    public Equipo() {}

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}

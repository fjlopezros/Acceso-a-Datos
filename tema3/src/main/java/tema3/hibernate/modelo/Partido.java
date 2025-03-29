package tema3.hibernate.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "partidos")
public class Partido {

    @Id
    @Column(name = "Codigo")
    private int codigo;

    @ManyToOne
    @JoinColumn(name = "Equipo_local")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "Equipo_visitante")
    private Equipo equipoVisitante;

    @Column(name = "Puntos_local")
    private int puntosLocal;

    @Column(name = "Puntos_visitante")
    private int puntosVisitante;

    @Column(name = "Temporada")
    private String temporada;

    // Constructor por defecto
    public Partido() {}

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(int puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public int getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(int puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }
}

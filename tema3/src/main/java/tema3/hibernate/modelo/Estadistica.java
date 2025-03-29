package tema3.hibernate.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "estadisticas")
public class Estadistica {

    @Id
    @Column(name = "Codigo")
    private int codigo;

    @OneToOne
    @JoinColumn(name = "Jugador")
    private Jugador jugador;

    @Column(name = "Puntos_por_partido")
    private float puntosPorPartido;

    @Column(name = "Asistencias_por_partido")
    private float asistenciasPorPartido;

    @Column(name = "Tapones_por_partido")
    private float taponesPorPartido;

    @Column(name = "Rebotes_por_partido")
    private float rebotesPorPartido;

    // Constructor por defecto
    public Estadistica() {}

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public float getPuntosPorPartido() {
        return puntosPorPartido;
    }

    public void setPuntosPorPartido(float puntosPorPartido) {
        this.puntosPorPartido = puntosPorPartido;
    }

    public float getAsistenciasPorPartido() {
        return asistenciasPorPartido;
    }

    public void setAsistenciasPorPartido(float asistenciasPorPartido) {
        this.asistenciasPorPartido = asistenciasPorPartido;
    }

    public float getTaponesPorPartido() {
        return taponesPorPartido;
    }

    public void setTaponesPorPartido(float taponesPorPartido) {
        this.taponesPorPartido = taponesPorPartido;
    }

    public float getRebotesPorPartido() {
        return rebotesPorPartido;
    }

    public void setRebotesPorPartido(float rebotesPorPartido) {
        this.rebotesPorPartido = rebotesPorPartido;
    }
}

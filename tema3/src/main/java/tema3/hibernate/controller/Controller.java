package tema3.hibernate.controller;

import tema3.hibernate.modelo.Jugador;
import tema3.hibernate.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static tema3.hibernate.main.Main.getSession;

/**
 * Clase controller, para darle funcionalidad a los botones de la View, aparte la hace visible.
 */
public class Controller implements ActionListener {
    private View view;

    private static List<Jugador> jugadores;
    private static int posicionActual;

    public Controller(View view) {
        this.view = view;


        view.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ejecutar Consulta" -> cargarJugadores();
            case "Siguiente" -> siguiente();
            case "Anterior" -> anterior();
            case "Primer Reg" -> primerReg();
            case "Ultimo Reg" -> ultimoReg();
        }
    }

    /**
     * Cargamos una lista de todos los fugadores en order ascendente
     * Si no es nula y no esta vacia mostramos los jugadores en la posicion actual *0, La primera*
     * Si no cumple las condiciones mostramos una alerta de error
     */
    private void cargarJugadores() {
        jugadores = getSession().createQuery("FROM Jugador ORDER BY codigo ASC", Jugador.class).list();
        if(jugadores!= null && !jugadores.isEmpty()){
            posicionActual = 0;
            mostrarJugador(jugadores.get(posicionActual));
        }else{
            JOptionPane.showMessageDialog(null, "No se encontraron jugadores", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *Metodo para ir hacia atras
     * Comprobaciones de la lista y decrementamos la posicion. Mostramos.
     * Si es nulo o esta vacia muestra una alerta de error
     */
    private void anterior() {
        if (jugadores != null && posicionActual > 0) {
            posicionActual--;
            mostrarJugador(jugadores.get(posicionActual));
        } else {
            JOptionPane.showMessageDialog(null, "No hay jugadores anteriores.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *Metodo para ir hacia delante
     * Comprobaciones de la lista y aunmentamos la posicion. Mostramos.
     * Si es nulo o esta vacia muestra una alerta de error
     */
    private void siguiente() {
        if (jugadores != null && posicionActual < jugadores.size() - 1) {
            posicionActual++;
            mostrarJugador(jugadores.get(posicionActual));
        } else {
            JOptionPane.showMessageDialog(null, "No hay más jugadores.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * metodo para mostrar el primer registr *equivalente a cargarJugadores*
     * Si no es nula o no esta vacia muestra la primera posicion
     * De lo contrario muestra una alerta de error
     */
    private void primerReg() {
        if (jugadores != null && !jugadores.isEmpty()) {
            posicionActual = 0;
            mostrarJugador(jugadores.get(posicionActual));
        } else {
            JOptionPane.showMessageDialog(null, "No hay más jugadores.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * metodo para mostar el ultimo registro
     * Si no es nula o no esta vacia muestra la ultima posicion
     * De lo contrario muestra una alerta de error
     */
    private void ultimoReg() {
        if (jugadores != null && !jugadores.isEmpty()) {
            posicionActual = jugadores.size()-1;
            mostrarJugador(jugadores.get(posicionActual));
        } else {
            JOptionPane.showMessageDialog(null, "No hay más jugadores.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Metodo que cambia el valor de los TextField de la vista por el jugador introducido
     * @param jugador jugador que cogemos para cambiar los datos de la vista
     */
    private void mostrarJugador(Jugador jugador) {
        view.getTfCodigo().setText(Integer.toString(jugador.getCodigo()));
        view.getTfNombre().setText(jugador.getNombre());
        view.getTfProcedencia().setText(jugador.getProcedencia());
        view.getTfPosicion().setText(jugador.getPosicion());
        view.getTfNombreEquipo().setText(jugador.getEquipo().getNombre());
    }
}

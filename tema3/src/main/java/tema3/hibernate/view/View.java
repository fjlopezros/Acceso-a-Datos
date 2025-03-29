package tema3.hibernate.view;

import tema3.hibernate.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    // DECLARACIÓN
    private JButton btEjecutar, btPrimerR, btUltimoR, btSiguiente, btAnterior;
    private JLabel lbCodigo, lbNombre, lbProcedencia, lbPosicion, lbNombreEquipo, lbTitulo;
    private JPanel panelBt, panelPrincipal, panelNorte, panelCentro;
    private JTextField tfCodigo, tfNombre, tfProcedencia, tfPosicion,tfNombreEquipo;

    private Controller controller = new Controller(this);

    public View() {
        // PREDEFINIDO
        this.setTitle("CONSULTA DE JUGADORES");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 500, 300);
        this.setLayout(new BorderLayout());

        // PANELES
        panelNorte = new JPanel();
        panelPrincipal = new JPanel(new BorderLayout());
        panelCentro = new JPanel(new GridLayout(5, 2));
        panelBt = new JPanel();

        // BOTONES
        btEjecutar = new JButton("Ejecutar Consulta");
        btPrimerR = new JButton("Primer Reg");
        btSiguiente = new JButton("Siguiente");
        btAnterior = new JButton("Anterior");
        btUltimoR = new JButton("Ultimo Reg");

        // FUNCIONALIDAD
        btEjecutar.setActionCommand("Ejecutar Consulta");
        btEjecutar.addActionListener(controller);
        btPrimerR.setActionCommand("Primer Reg");
        btPrimerR.addActionListener(controller);
        btSiguiente.setActionCommand("Siguiente");
        btSiguiente.addActionListener(controller);
        btAnterior.setActionCommand("Anterior");
        btAnterior.addActionListener(controller);
        btUltimoR.setActionCommand("Ultimo Reg");
        btUltimoR.addActionListener(controller);

        // ETIQUETAS
        lbTitulo = new JLabel("CONSULTA DE JUGADORES", JLabel.CENTER);
        lbCodigo = new JLabel("Código: ");
        lbNombre = new JLabel("Nombre: ");
        lbProcedencia = new JLabel("Procedencia: ");
        lbPosicion = new JLabel("Posición: ");
        lbNombreEquipo = new JLabel("Nombre del equipo: ");

        //AREA NO EDITABLE
        tfCodigo = new JTextField();
        tfNombre = new JTextField();
        tfProcedencia = new JTextField();
        tfPosicion = new JTextField();
        tfNombreEquipo = new JTextField();

        tfCodigo.setEditable(false);
        tfNombre.setEditable(false);
        tfProcedencia.setEditable(false);
        tfPosicion.setEditable(false);
        tfNombreEquipo.setEditable(false);

        // AÑADIR A PANELES
        panelNorte.add(lbTitulo);
        panelNorte.add(btEjecutar, BorderLayout.EAST);

        panelCentro.add(lbCodigo);
        panelCentro.add(tfCodigo);
        panelCentro.add(lbNombre);
        panelCentro.add(tfNombre);
        panelCentro.add(lbProcedencia);
        panelCentro.add(tfProcedencia);
        panelCentro.add(lbPosicion);
        panelCentro.add(tfPosicion);
        panelCentro.add(lbNombreEquipo);
        panelCentro.add(tfNombreEquipo);

        panelBt.add(btPrimerR);
        panelBt.add(btSiguiente);
        panelBt.add(btAnterior);
        panelBt.add(btUltimoR);

        panelPrincipal.add(panelNorte, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelBt, BorderLayout.SOUTH);

        // AÑADIR A VISTA
        this.add(panelPrincipal);
        this.setVisible(true);
    }

    //GETTERS
    public JTextField getTfNombreEquipo() {
        return tfNombreEquipo;
    }

    public JTextField getTfPosicion() {
        return tfPosicion;
    }

    public JTextField getTfProcedencia() {
        return tfProcedencia;
    }

    public JTextField getTfNombre() {
        return tfNombre;
    }

    public JTextField getTfCodigo() {
        return tfCodigo;
    }
}

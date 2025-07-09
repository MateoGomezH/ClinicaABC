package view;

import controller.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    private Controlador controlador;
    private JButton btnGestionarPersonas;
    private JButton btnGestionarMascotas;

    public VentanaPrincipal(Controlador controlador) {
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("Clínica Veterinaria ABC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/fondo.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        ImageIcon fondo = new ImageIcon(imagenEscalada);
        JLabel lblFondo = new JLabel(fondo);
        lblFondo.setBounds(0, 0, getWidth(), getHeight());
        setContentPane(lblFondo);
        lblFondo.setLayout(null);

        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        panelBotones.setBounds(0, getHeight() - 90, getWidth(), 60);

        btnGestionarPersonas = new JButton("Gestionar Personas");
        btnGestionarMascotas = new JButton("Gestionar Mascotas");

        panelBotones.add(btnGestionarPersonas);
        panelBotones.add(btnGestionarMascotas);

        lblFondo.add(panelBotones);

        // Acciones de los botones
        btnGestionarPersonas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaGestionarPersonas();
            }
        });

        btnGestionarMascotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaGestionarMascotas();
            }
        });
    }

    private void abrirVentanaGestionarPersonas() {
        VentanaGestionarPersonas ventanaPersonas = new VentanaGestionarPersonas(controlador);
        ventanaPersonas.setVisible(true);
    }

    private void abrirVentanaGestionarMascotas() {
        VentanaGestionarMascotas ventanaMascotas = new VentanaGestionarMascotas(controlador);
        ventanaMascotas.setVisible(true);
    }

    /* public static void main(String[] args) {
        Controlador controlador = new Controlador();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(controlador);
        ventanaPrincipal.setVisible(true);
    }*/
}

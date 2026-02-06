/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3abstract;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author janinadiaz
 */
public class VentanaGUI extends JFrame implements Validacion{
    
    private JTextField txtJugador1;
    private JTextField txtJugador2;
    private JButton btnIniciar;
    private JLabel lblTitulo;
    private JLabel lblJugador1;
    private JLabel lblJugador2;
    
    public VentanaGUI() {
        configurarVentana();
        inicializarComponentes();
        configurarLayout();
    }
    
    private void configurarVentana() {
        setTitle("Pokemon Memory Game - Inicio");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void inicializarComponentes() {
        // Título
        lblTitulo = new JLabel("POKEMON MEMORY GAME");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 203, 5)); // Color amarillo Pokemon
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Labels
        lblJugador1 = new JLabel("Nombre Jugador 1:");
        lblJugador1.setFont(new Font("Arial", Font.PLAIN, 16));
        
        lblJugador2 = new JLabel("Nombre Jugador 2:");
        lblJugador2.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Text fields
        txtJugador1 = new JTextField(20);
        txtJugador1.setFont(new Font("Arial", Font.PLAIN, 14));
        
        txtJugador2 = new JTextField(20);
        txtJugador2.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Botón iniciar
        btnIniciar = new JButton("INICIAR JUEGO");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 16));
        btnIniciar.setBackground(new Color(255, 203, 5));
        btnIniciar.setForeground(Color.BLACK);
        btnIniciar.setFocusPainted(false);
        btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });
    }
    
    private void configurarLayout() {
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(220, 20, 60)); // Color rojo Pokemon
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
   
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(220, 20, 60));
        panelTitulo.add(lblTitulo);
        

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
       
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(lblJugador1, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelFormulario.add(txtJugador1, gbc);
        
        // Jugador 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(lblJugador2, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelFormulario.add(txtJugador2, gbc);
        
        // Botón
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        panelFormulario.add(btnIniciar, gbc);
        
        // Agregar paneles
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        
        add(panelPrincipal);
    }
    
    private void iniciarJuego() {
        try {
            if (validarDatos()) {
                String nombreJ1 = txtJugador1.getText().trim();
                String nombreJ2 = txtJugador2.getText().trim();
                
                // Crear y mostrar ventana de juego
                JuegoGUI ventanaJuego = new JuegoGUI(nombreJ1, nombreJ2);
                ventanaJuego.setVisible(true);
                
                // Cerrar ventana de inicio
                this.dispose();
            }
        } catch (Exception e) {
            mostrarErrorValidar("Error al iniciar el juego: " + e.getMessage());
        }
    }
    
    @Override
    public boolean validarDatos() {
        try {
            String nombreJ1 = txtJugador1.getText().trim();
            String nombreJ2 = txtJugador2.getText().trim();
            
            if (!validadNombre(nombreJ1)) {
                mostrarErrorValidar("Por favor ingrese el nombre del Jugador 1");
                return false;
            }
            
            if (!validadNombre(nombreJ2)) {
                mostrarErrorValidar("Por favor ingrese el nombre del Jugador 2");
                return false;
            }
            
            if (nombreJ1.equalsIgnoreCase(nombreJ2)) {
                mostrarErrorValidar("Los nombres de los jugadores deben ser diferentes");
                return false;
            }
            
            return true;
        } catch (Exception e) {
            mostrarErrorValidar("Error en la validación");
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean validadNombre(String nombre) {
        return nombre != null && !nombre.isEmpty() && nombre.length() >= 2;
    }
    
    @Override
    public void mostrarErrorValidar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Validación", 
                                      JOptionPane.ERROR_MESSAGE);
    }
    //
   
}

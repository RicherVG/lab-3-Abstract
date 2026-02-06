/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3abstract;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author janinadiaz
 */
public class JuegoGUI extends JFrame implements Validacion{
    private ControlJuego controlador;
    private JPanel panelTablero;
    private JLabel lblTurno;
    private JLabel lblPuntajeJ1;
    private JLabel lblPuntajeJ2;
    private JButton btnReiniciar;
    private Timer timerOcultarCartas;
    private boolean esperandoOcultar;
    
    public JuegoGUI(String nombreJ1, String nombreJ2) {
        try{
            controlador = new ControlJuego(nombreJ1, nombreJ2);
            controlador.iniciarJuego();
            esperandoOcultar = false;
            
            configurarVentana();
            inicializarComponentes();
            configurarLayout();
            actualizarInterfaz();
        }catch (Exception e) {
            mostrarErrorValidar("Error al crear la ventana de juego");
        }
    }
    
    private void configurarVentana(){
        setTitle("Pokemon Juego Memoria");
        setSize(900,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    private void inicializarComponentes(){
        lblTurno = new JLabel();
        lblTurno.setFont(new Font("Arial",Font.BOLD,18) );
        lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblPuntajeJ1 = new JLabel();
        lblPuntajeJ1.setFont(new Font("Arial",Font.PLAIN,16));
        
        lblPuntajeJ2 = new JLabel();
        lblPuntajeJ2.setFont(new Font("Arial",Font.PLAIN,16));
        
        //boton para reinicair
        btnReiniciar = new JButton("Reiniciar Juego");
        btnReiniciar.setFont(new Font("Arial",Font.BOLD,14));
        btnReiniciar.setBackground(new Color(255,203,5));
        btnReiniciar.setFocusPainted(false);
        btnReiniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                reiniciarJuego();
            }
        });
        
        // panels para el tablero
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(6, 6,10,10));
        panelTablero.setBackground(new Color(50, 50, 50));
        panelTablero.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        crearTablero();
        
        // Timer para ocultar cart
        timerOcultarCartas = new Timer(1500,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                procesarResultado();
            }
        });
        timerOcultarCartas.setRepeats(false);
    }
    
    private void crearTablero(){
        try{
            for(final Carta carta : controlador.getTablero().getCartas()){
                if(carta instanceof CartaPokemon){
                    CartaPokemon cartaPokemon=(CartaPokemon)carta;
                    JButton boton=cartaPokemon.getBoton();
                    
                    
                    boton.setPreferredSize(new Dimension(100, 100));
                    
                    boton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                            manejarClickCarta(carta);
                        }
                    });
                    
                    
                    
                    panelTablero.add(boton);
                }
            }
        } catch(Exception e){
            System.err.println("Error al crear tablero");

        }
        
        
        
    }
    
    private void configurarLayout() {
        JPanel panelPrincipal =new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(220, 20, 60));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // panel arriba
        JPanel panelInfo = new JPanel(new GridLayout(4, 1, 5, 5));
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        JLabel lblTitulo = new JLabel("POKEMON JUEGO de MEMORIA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD,20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        panelInfo.add(lblTitulo);
        panelInfo.add(lblTurno);
        panelInfo.add(lblPuntajeJ1);
        panelInfo.add(lblPuntajeJ2);
        
        // panel abajo
        JPanel panelInferior=new JPanel();
        panelInferior.setBackground(new Color(220, 20, 60));
        panelInferior.add(btnReiniciar);
        
        panelPrincipal.add(panelInfo, BorderLayout.NORTH);
        panelPrincipal.add(panelTablero, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private void manejarClickCarta(Carta carta){
        try {
            if(esperandoOcultar||carta.estaDescubierta()){
                return;
            }
            
            boolean intentoCompleto=controlador.seleccionarCarta(carta);
            
            if (intentoCompleto) {
                // Se seleccionaron dos cartas
                esperandoOcultar = true;
                deshabilitarTablero();
                timerOcultarCartas.start();
            }
        } catch (Exception e) {
            mostrarErrorValidar("Error al procesar selección de carta");

        }
    }
    
    private void procesarResultado(){
        try{
            boolean esPareja=controlador.procesarIntento();
            
            if(!esPareja){
                controlador.cambiarTurno();
            }
            
            esperandoOcultar = false;
            habilitarTablero();
            actualizarInterfaz();
            
            // Verificar si el juego terminó
            if(controlador.ifJuegoTermino()){
                controlador.finalizarJuego();
                mostrarGanador();
            }
            
        }catch(Exception e) {
            System.err.println("Error al procesar resultado");
            
            
        }
    }
    
    private void actualizarInterfaz() {
        try{
            Jugador jugadorActual = controlador.getJugadorActual();
            Jugador j1 = controlador.getJugador1();
            Jugador j2 = controlador.getJugador2();
            
            lblTurno.setText("Turno: " + jugadorActual.getNombre());
            lblTurno.setForeground(jugadorActual == j1 ? Color.BLUE : Color.RED);
            
            //update los puntos
            lblPuntajeJ1.setText(j1.getNombre() + ": " + j1.getAciertos() + " parejas");
            lblPuntajeJ2.setText(j2.getNombre() + ": " + j2.getAciertos() + " parejas");
        } catch(Exception e) {
            System.err.println("Error al actualizar interfaz");
        }
    }
    
    private void deshabilitarTablero(){
        
        for(Carta carta:controlador.getTablero().getCartas()){
            //instanceof para ver si la carata es una en especifico
            if(carta instanceof CartaPokemon){
                CartaPokemon cp=(CartaPokemon)carta;
                if(!carta.estaDescubierta()){
                    cp.getBoton().setEnabled(false);
                }
            }
            
        }
    }
    
    private void habilitarTablero(){
        for(Carta carta : controlador.getTablero().getCartas()) {
            if(carta instanceof CartaPokemon) {
                CartaPokemon cp = (CartaPokemon) carta;
                if (!carta.estaDescubierta()) {
                    cp.getBoton().setEnabled(true);
                }
                
                
            }
            
        }
    }
    
    private void mostrarGanador(){
        try{
            String mensaje=controlador.determinarGanadordelJuego();
            
            String estadisticas="\n\nEstadísticas finales:\n" +
                    controlador.getJugador1().getNombre() + ": " + 
                    controlador.getJugador1().getAciertos() + " parejas\n" +
                    controlador.getJugador2().getNombre() + ": " + 
                    controlador.getJugador2().getAciertos() + " parejas";
            
            JOptionPane.showMessageDialog(this, mensaje + estadisticas,"¡Juego Terminado!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e){
            System.err.println("Error al mostrar ganador");
        }
    }
    
    private void reiniciarJuego(){
        try{
            int opcion=JOptionPane.showConfirmDialog(this, "¿Estás seguro que deseas reiniciar el juego?", "Confirmar reinicio",JOptionPane.YES_NO_OPTION);
            
            if (opcion==JOptionPane.YES_OPTION){
                this.dispose();
                VentanaGUI ventanaInicio=new VentanaGUI();
                ventanaInicio.setVisible(true);
            }
        } catch(Exception e) {
            mostrarErrorValidar("Error al reiniciar el juego");

        }
    }
    
    @Override
    public boolean validarDatos(){

        return true;
    }
    
    @Override
    public boolean validadNombre(String nombre){
        return nombre!=null && !nombre.isEmpty();
    }
    
    @Override
    public void mostrarErrorValidar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error",JOptionPane.ERROR_MESSAGE);
    }
}

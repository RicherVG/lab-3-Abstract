/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3abstract;

import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 *
 * @author riche
 */
public class CartaPokemon extends Carta {
    private JButton boton;
    private static final String IMAGEN_DORSO="/resources/dorso.png";

    public CartaPokemon(String imagen, int id) {
        super(imagen, id);
        this.boton = new JButton();
        configurarBoton();
    }
    
    private void configurarBoton(){
        boton.setIcon(cargarIcono(IMAGEN_DORSO));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
    }
    
    @Override
    public void mostrar() {
        try {
          
            boton.setIcon(cargarIcono("/" + imagen));
            descubierta = true;
        } catch (Exception e) {
            System.err.println("Error al cargar imagen: " + imagen);
        }

    }

    @Override
    public void ocultar() {
       boton.setIcon(cargarIcono(IMAGEN_DORSO));
       descubierta=false;
    }
    public JButton getBoton(){
        return boton;
    }
    
    public void desactivar(){
        boton.setEnabled(false);
    }
    private ImageIcon cargarIcono(String ruta) {
        java.net.URL url = getClass().getResource(ruta);
        if (url == null) {
            System.err.println("No se encontró el recurso: " + ruta);
            return new ImageIcon(); 
        }
        return new ImageIcon(url);
    }
}

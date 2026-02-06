/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab3abstract;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author riche
 */
public class Lab3Abstract {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        try {
            // Establecer look and feel del sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaGUI ventana = new VentanaGUI();
                ventana.setVisible(true);
            }
        });
    }
    
}

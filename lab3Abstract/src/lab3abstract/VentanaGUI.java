/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3abstract;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author janinadiaz
 */
public class VentanaGUI extends JFrame implements Validacion{
    
    
    
    
    
    public boolean validarNombre(String nombre) {
        return nombre!=null && !nombre.isEmpty() && nombre.length()>= 2;
    }
    
    
    @Override
    public void mostrarErrorValidar(String error) {
        JOptionPane.showMessageDialog(this, error, "Error de Validación", 
        JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public boolean validarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean validadNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3abstract;

/**
 *
 * @author janinadiaz
 */
public class Jugador {
    private String nombre;
    private int aciertos;
    
    public Jugador(String nombre){
        this.nombre=nombre;
        this.aciertos=0;
    }
    public String getNombre(){
        return this.nombre;
    }
    public int getAciertos(){
        return aciertos;
    }
    
    public void subirAciertos(){
        this.aciertos++;
    }
    public void restartAciertos(){
        this.aciertos=0;
    }
    
    @Override
    public String toString(){
        return this.nombre+": "+this.aciertos+" Pares";
    }
}

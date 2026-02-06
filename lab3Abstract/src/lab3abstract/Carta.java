/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3abstract;

/**
 *
 * @author riche
 */
public abstract class Carta {
    protected String imagen;
    protected boolean descubierta;
    protected int id;
    
    public Carta(String imagen,int id){
        this.imagen=imagen;
        this.descubierta=false;
        this.id=id;
    }
    
    public abstract void mostrar();
    public abstract void ocultar();
    public boolean estaDescubierta(){
        return descubierta;
    }
    
    public void setDescubierta(boolean descubierta){
        this.descubierta=descubierta;
    }
    
    public String getImagen(){
        return imagen;
    }
    
    public int getId(){
        return id;
    }
    
    public boolean esParejaCon(Carta otraCarta){
        return this.id== otraCarta.getId();
    }
}

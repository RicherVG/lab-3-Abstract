/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3abstract;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 *
 * @author janinadiaz
 */
public class Tablero {
    private static final int FILAS=6;
    private static final int COLUMNAS=6;
    private static final int TOTAL_PAREJAS=18;
    
    private List<Carta> cartas;
    private int parejasEncontradas;
    
    public Tablero(){
        cartas= new ArrayList<>();
        parejasEncontradas=0;
        inicializarCartas();
    }
    
    private void inicializarCartas(){
        String[] pokemonNombres = {
            "pikachu", "charmander", "bulbasaur", "squirtle",
            "eevee", "meowth", "psyduck", "jigglypuff",
            "snorlax", "mewtwo", "dragonite", "gengar",
            "lapras", "gyarados", "alakazam", "machamp",
            "charizard", "blastoise"
        };
        
        try{
            for (int i = 0; i < TOTAL_PAREJAS; i++) {
                String rutaImagen = "resources/"+pokemonNombres[i]+".png";
                
                cartas.add(new CartaPokemon(rutaImagen,i));
                cartas.add(new CartaPokemon(rutaImagen,i));
            }
            
            Collections.shuffle(cartas);
        } catch (Exception e) {
            System.err.println("Error al inicializar las cartas del tablero");    
            }      
    }
    public List<Carta> getCartas() {
        return cartas;
    }

    public int getFilas() {
        return FILAS;
    }

    public int getColumnas() {
        return COLUMNAS;
    }
    
    public void incrementarParejasEncontradas(){
        parejasEncontradas++;
    }
    
    public boolean todasParejasEncontradas(){
        return parejasEncontradas==TOTAL_PAREJAS;
    }
    
    public int getParejasEncontradas(){
        return parejasEncontradas;
    }
}

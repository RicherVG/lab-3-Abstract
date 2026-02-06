/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lab3abstract;

/**
 *
 * @author janinadiaz
 */
public interface Juego {
    void iniciarJuego();
    void finalizarJuego();
    void cambiarTurno();
    boolean verificarParejaCartas(Carta carta1, Carta carta2);
    boolean ifJuegoTermino();
    
}

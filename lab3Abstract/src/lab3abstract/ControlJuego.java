/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3abstract;

/**
 *
 * @author janinadiaz
 */
public class ControlJuego implements Juego{
    
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugador;
    private Tablero tablero;
    private Carta primeraCarta;
    private Carta segundaCarta;
    private int intentos;
    
    
    public ControlJuego(String nombreJ1, String nombreJ2){
        try{
            this.jugador1 = new Jugador(nombreJ1);
            this.jugador2 = new Jugador(nombreJ2);
            this.jugador = jugador1;
            this.tablero = new Tablero();
            this.primeraCarta = null;
            this.segundaCarta = null;
            this.intentos = 0;
            
        } catch (Exception e){
            System.err.println("Error al crear el contro del juego");
        }
    }
    
    @Override
    public void iniciarJuego(){
        jugador1.restartAciertos();
        jugador2.restartAciertos();
        jugador = jugador1;
        primeraCarta = null;
        segundaCarta = null;
        intentos = 0;
    }
    
    @Override
    public void cambiarTurno(){
        if (jugador == jugador1){
        jugador = jugador2;
        }else{
            jugador = jugador1;
        }
    }
    
    @Override
    public boolean verificarParejaCartas(Carta carta1, Carta carta2){
        try{
            if (carta1 == null || carta2 == null){
                return false;
            }
            return carta1.esParejaCon(carta2);
        }catch (Exception e){
            System.err.println("Error verificando pareja!");

            return false;
        }
    }
    
    @Override
    public void finalizarJuego(){
        
    }
    
    @Override
    public boolean ifJuegoTermino(){
        return tablero.todasParejasEncontradas();
    }
    
    public boolean seleccionarCarta (Carta cartas){
        try{
            if (cartas.estaDescubierta()){
                return false;
            }
            
            if (primeraCarta == null){
                primeraCarta = cartas;
                primeraCarta.mostrar();
                intentos = 1;
                return false;
            }else if (segundaCarta == null && cartas != primeraCarta){
                segundaCarta = cartas;
                segundaCarta.mostrar();
                intentos = 2;
                return true;
            }
            
            return false;
        }catch (Exception e){
            System.err.print("Error seleccionando carta");
            return false;
        }
    }
    
    public boolean procesarIntento(){
        
        try{
            if (primeraCarta == null || segundaCarta == null) {
                return false;
            }
            
            boolean esPareja = verificarParejaCartas(primeraCarta, segundaCarta);
            
            if (esPareja){
                primeraCarta.setDescubierta(true);
                segundaCarta.setDescubierta(true);
                jugador.subirAciertos();
                tablero.incrementarParejasEncontradas();
                
            if(primeraCarta instanceof CartaPokemon){
                ((CartaPokemon)primeraCarta).desactivar();
            }
            if(segundaCarta instanceof CartaPokemon){
                ((CartaPokemon)segundaCarta).desactivar();
            }
            }else{
                primeraCarta.ocultar();
                segundaCarta.ocultar();
            }
            
            primeraCarta = null;
            segundaCarta = null;
            intentos = 0;
            
            return esPareja;
        }catch (Exception e){
                System.err.println("Error procesando intento");
                return false;
                }
    }
    
    public Jugador getJugador1(){
        return jugador1;
    }
    
    public Jugador getJugador2(){
        return jugador2;
    }
    public Jugador getJugadorActual() {
        return jugador;
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    public int intentos(){
        return intentos;
    }
    
    public String determinarGanadordelJuego(){
        try{
            int aciertosJ1 = jugador.getAciertos();
            int aciertosJ2 = jugador.getAciertos();
            
            if(aciertosJ1>aciertosJ2){
                return "¡Felicitaciones " + jugador1.getNombre() + "gano con" + aciertosJ1 + "parejas!";
            }else if (aciertosJ2>aciertosJ1){
                return "¡Felicitaciones" + jugador2.getNombre() + "gano con" + aciertosJ2 + "parejas!";
            }
            else{
                return "¡Empate! ambos jugadores tienen "+aciertosJ1+" parejas!";
            }
        }catch (Exception e){
            System.err.println("Error determinando el ganador.");

            return "Error determinando el ganador.";
            }

        }
    }

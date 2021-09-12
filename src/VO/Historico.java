/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

/**
 *
 * @author josep
 */
public class Historico {
    
    int id;
    String jugador;
    int ronda_alcanzada;
    int premio;
    String clasificacion;  // Ganó, perdió, se retiró

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getRonda_alcanzada() {
        return ronda_alcanzada;
    }

    public void setRonda_alcanzada(int ronda_alcanzada) {
        this.ronda_alcanzada = ronda_alcanzada;
    }

    public int getPremio() {
        return premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    
    
}

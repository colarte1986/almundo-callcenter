/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.callcenter.entidades;

/**
 *
 * @author carlosolarte
 */
public enum TipoColaborador {
    OPERADOR(1),
    SUPERVISOR(2),
    DIRECTOR(3);
    private int prioridad;

    private TipoColaborador(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getPrioridad() {
        return prioridad;
    }
    
    
    
    
}

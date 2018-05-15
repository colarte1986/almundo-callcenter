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
public class Llamada {
    private int duracion;
    private String descripcion;

    public Llamada(int duracion, String descripcion) {
        this.duracion = duracion;
        this.descripcion = descripcion;
    }
    
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}

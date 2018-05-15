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
public abstract class Empleado implements Comparable<Empleado>{
    public TipoColaborador tipoColaborador;
    private String nombre;
    
    public Empleado(String nombre){
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Empleado e) {
        if(this.tipoColaborador.getPrioridad() < e.tipoColaborador.getPrioridad()){
            return -1;
        }
        if(this.tipoColaborador.getPrioridad() > e.tipoColaborador.getPrioridad()){
            return 1;
        }
        return 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}

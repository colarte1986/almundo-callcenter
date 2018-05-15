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
public class Director extends Empleado{
    
    public Director(String nombre) {
        super(nombre);
        super.tipoColaborador = TipoColaborador.DIRECTOR;
    }
    
    public void addEmpleado(){};
    
}

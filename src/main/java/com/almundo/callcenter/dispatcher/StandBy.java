/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.callcenter.dispatcher;

/**
 *
 * @author carlosolarte
 */
public class StandBy implements Runnable {
 
    @Override
    public void run() {
    System.out.println("Todos los operadores están ocupados");
    }
 
}

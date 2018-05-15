/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.callcenter.dispatcher;

import com.almundo.callcenter.entidades.Empleado;
import com.almundo.callcenter.entidades.Llamada;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlosolarte
 */
public class CallDispatcher extends Thread{
    BlockingQueue<Empleado> empleados;
    BlockingQueue<Llamada> llamadas;
    CyclicBarrier standByMessage;

    public CallDispatcher(CyclicBarrier standByMessage) {
        this.empleados = new PriorityBlockingQueue<>();
        this.llamadas = new LinkedBlockingDeque<>();
        this.standByMessage = standByMessage;
        
    }
    
    public void dispatchCall(Llamada llamada){
        try {
            this.llamadas.put(llamada);
        } catch (InterruptedException ex) {
            Logger.getLogger(CallDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            Llamada llamada;
            Empleado empleado;
            while(true){
                standByMessage.await();
                llamada = llamadas.take();
                empleado = empleados.take();
                System.out.println(empleado.getNombre() + " va a tomar la llamada " + llamada.getDescripcion());
                //Se inicia la atención de la llamada por parte del empleado
                //Se para el hilo durante la duración de la llamada
                //Una vez se termina la duración de la llamada el empleado vuelve a quedar disponible 
                // y se adiciona nuevamente a la cola de empleados
                Thread.sleep(TimeUnit.SECONDS.toMillis(llamada.getDuracion())); //milisegundos
                System.out.println("Finaliza la llamada " + llamada.getDescripcion());
                this.empleados.add(empleado);
                System.out.println("Empleado " + empleado.getNombre() + " disponible.");
            }
        } catch (Exception e) {
            System.out.println("Error atendiendo la llamada ");
        }
    }

    public BlockingQueue<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(BlockingQueue<Empleado> empleados) {
        this.empleados = empleados;
    }

    public BlockingQueue<Llamada> getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(BlockingQueue<Llamada> llamadas) {
        this.llamadas = llamadas;
    }
    
    
    public void addEmpleado(Empleado empleado){
        this.empleados.add(empleado);
    }
    
    
}

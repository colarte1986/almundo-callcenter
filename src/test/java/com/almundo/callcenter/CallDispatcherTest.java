/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almundo.callcenter;

import com.almundo.callcenter.dispatcher.CallDispatcher;
import com.almundo.callcenter.dispatcher.StandBy;
import com.almundo.callcenter.entidades.Director;
import com.almundo.callcenter.entidades.Llamada;
import com.almundo.callcenter.entidades.Operador;
import com.almundo.callcenter.entidades.Supervisor;
import java.util.concurrent.CyclicBarrier;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author carlosolarte
 */
public class CallDispatcherTest extends TestCase {

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CallDispatcherTest.class);
    }

    //El primer test a realizar dura 15 segundos
    //con un solo empleado de tipo operador
    //quien debería poder atender todas las llamadas
    public void testTresLLamadas() {
        try {
            System.out.println("-----------Testeando 3 llamadas--------------");
            CyclicBarrier standbyMessage = new CyclicBarrier(1, new StandBy());
            CallDispatcher dispatcher = new CallDispatcher(standbyMessage);
            dispatcher.start();
            dispatcher.addEmpleado(new Operador("Operador 1"));
            Llamada llamada1 = new Llamada(5, " 1 ");
            Llamada llamada2 = new Llamada(5, " 2 ");
            Llamada llamada3 = new Llamada(5, " 3 ");
            dispatcher.dispatchCall(llamada1);
            dispatcher.dispatchCall(llamada2);
            dispatcher.dispatchCall(llamada3);

            //Las llamadas de este test duran 5s y como hay 1 solo operador, las llamadas tienen que ser atendidasen 15s.
            Thread.sleep(15000);

            //Al finalizar este tiempo, las llamdas deberian haber sido atendidas
            assertTrue(dispatcher.getLlamadas().isEmpty());

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        }

    }

    public void testDiezLLamadas() {
        System.out.println("-----------Testeando 10 llamadas--------------");
        try {
            CyclicBarrier standbyMessage = new CyclicBarrier(1, new StandBy());
            CallDispatcher dispatcher = new CallDispatcher(standbyMessage);
            dispatcher.start();
            dispatcher.addEmpleado(new Director("Director 1"));
            dispatcher.addEmpleado(new Supervisor("Supervisor 1"));
            dispatcher.addEmpleado(new Operador("Operador 1"));

            dispatcher.dispatchCall(new Llamada(10, " 1 "));
            dispatcher.dispatchCall(new Llamada(5, " 2 "));
            dispatcher.dispatchCall(new Llamada(4, " 3 "));
            dispatcher.dispatchCall(new Llamada(3, " 4 "));
            dispatcher.dispatchCall(new Llamada(2, " 5 "));
            dispatcher.dispatchCall(new Llamada(4, " 6 "));
            dispatcher.dispatchCall(new Llamada(5, " 7 "));
            dispatcher.dispatchCall(new Llamada(4, " 8 "));
            dispatcher.dispatchCall(new Llamada(5, " 9 "));
            dispatcher.dispatchCall(new Llamada(5, " 10 "));

            //Espero el tiempo maximo para que todas las llamadas hayan sido atendidas:
            Thread.sleep(10000 * 10);

            //Al finalizar este tiempo, las llamdas deberian haber sido atendidas
            assertTrue(dispatcher.getLlamadas().isEmpty());

        } catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        }

    }
}

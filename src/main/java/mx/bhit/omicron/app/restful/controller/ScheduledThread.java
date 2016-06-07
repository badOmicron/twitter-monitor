/**
 * ScheduledThread.java
 * Fecha de creación: 07/06/2016, 15:34:37
 *
 * Copyright (c) 2016 Instituto Federal Electoral. Dirección
 * Ejecutiva del Registro Federal de Electores.
 * Periférico Sur 239, México, D.F., C.P. 01010.
 * Todos los derechos reservados.
 *
 * Este software es información confidencial, propiedad del
 * Instituto Federal Electoral. Esta información confidencial
 * no deberá ser divulgada y solo se podrá utilizar de acuerdo
 * a los términos que determine el propio Instituto.
 */

package mx.bhit.omicron.app.restful.controller;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * TODO [Agregar documentación de la clase]
 * @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
 * @version 1.0
 * @param <V>
 * @since SIIRFE 6.0
 */
public class ScheduledThread {

    private ScheduledExecutorService exec;

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public ScheduledThread() {
        // TODO [codificar el cuerpo del método]
        exec = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public void startMonitoreoTopicos() {
        exec.scheduleAtFixedRate(new Runnable() {
            int cont = 0;

            public void run() {
                // TODO [codificar el cuerpo del método]
                cont++;
                if (cont == 5) {
                    stopMonitoreoTopicos();
                }
                System.out.println(cont + " Fecha: " + new Date());
            }
        }, 0, 800, TimeUnit.MILLISECONDS);
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public void stopMonitoreoTopicos() {
        try {
            Thread.currentThread().join(0);
            System.out.println("Thread detenido de forma correcta...");
        } catch (InterruptedException e) {
            // TODO [Manejar la excepcion de forma correcta]
            System.out.println("Error al detener el thread...");
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        System.out.println("----------");
        ScheduledThread thread = new ScheduledThread();
        thread.startMonitoreoTopicos();
    }
}

/**
 * Trigger.java
 * Fecha de creación: 08/06/2016, 12:21:05
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

import java.util.Timer;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.bhit.omicron.app.restful.task.TwitterTask;
import mx.bhit.omicron.app.restful.utils.TwitterUtil;

/**
 * TODO [Agregar documentación de la clase]
 * @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
 * @version 1.0
 * @since SIIRFE 6.0
 */
public class Trigger {
    private static final Logger logger = LoggerFactory.getLogger(Trigger.class);

    private long timeSlot;
    private TwitterTask twitterTask;
    private static Timer timer;

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public Trigger() {
        twitterTask = new TwitterTask();
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param timeSlot
     */
    public void addConfiguration(String timeSlot) {
        this.timeSlot = Long.parseLong(timeSlot);
        this.timeSlot = TwitterUtil.miliToSec(this.timeSlot);
        logger.debug("Segundos: " + timeSlot);
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public static void stopMonitoreoTopicos() {
        timer.cancel();
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param thread
     */
    public void execute(Callable<?> thread) {
        timer = new Timer();
        twitterTask.addThread(thread);
        timer.schedule(twitterTask, 0, this.timeSlot);
    }

}

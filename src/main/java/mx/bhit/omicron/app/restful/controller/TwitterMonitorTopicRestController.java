package mx.bhit.omicron.app.restful.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.bhit.omicron.app.restful.model.Request;
import mx.bhit.omicron.app.restful.model.Response;
import mx.bhit.omicron.app.restful.model.SocialNetworksMonitoringConfProfResponse;
import mx.bhit.omicron.app.restful.service.TwitterMonitorThread;
import mx.bhit.omicron.app.restful.service.ValidationService;

/**
 * TODO [Agregar documentación de la clase]
 * @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
 * @version 1.0
 * @since SIIRFE 6.0
 */
@RestController
@RequestMapping("/monitor")
public class TwitterMonitorTopicRestController {
    private static final Logger logger = LoggerFactory.getLogger(TwitterMonitorTopicRestController.class);

    private Trigger trigger;
    private SocialNetworksMonitoringConfProfResponse socialNetworksMonitoringConfProfResponse;

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param request
     * @return
     */
    @RequestMapping("/on")
    public Response startMonitor(@RequestBody Request request) {
        String msgError = null;
        // Obtenemos los parametros
        String timeSlot = (String) request.getSocialNetworksMonitoringConfProf().get("timeSlot");
        String expression = (String) request.getSocialNetworksMonitoringConfProf().get("keywordExpression");

        socialNetworksMonitoringConfProfResponse = new SocialNetworksMonitoringConfProfResponse();
        ValidationService validationService = new ValidationService();

        if (trigger == null) {
            trigger = new Trigger();
        } else {
            msgError = "The monitor is running";
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
                "warning");
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
                msgError);
            return socialNetworksMonitoringConfProfResponse;

        }

        // Se validan las configuraciones para el hilo
        msgError = validationService.validaTiempo(timeSlot);
        if (msgError != null) {
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
                "error");
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
                msgError);
            return socialNetworksMonitoringConfProfResponse;
        }

        // Se valida la expresion
        msgError = validationService.validaExpresion(expression);
        if (msgError != null) {
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
                "error");
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
                "msgError");
            return socialNetworksMonitoringConfProfResponse;
        }

        // Si no hubo errores, se agrega la configuración del Thread
        trigger.addConfiguration(timeSlot);

        socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
            "success");
        socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
            "The monitor is up and running");

        TwitterMonitorThread TwitterMonitorThread = new TwitterMonitorThread();
        trigger.execute(TwitterMonitorThread);

        return socialNetworksMonitoringConfProfResponse;

    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @return
     */
    @RequestMapping("/off")
    public Response stopMonitor() {
        String msgError = null;
        socialNetworksMonitoringConfProfResponse = new SocialNetworksMonitoringConfProfResponse();

        if (trigger == null) {
            msgError = "the monitor is already off";
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
                "error");
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
                msgError);
            return socialNetworksMonitoringConfProfResponse;
        }
        Trigger.stopMonitoreoTopicos();
        msgError = "the monitor has stopped";
        socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
            "success");
        socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
            msgError);
        return socialNetworksMonitoringConfProfResponse;
    }
}

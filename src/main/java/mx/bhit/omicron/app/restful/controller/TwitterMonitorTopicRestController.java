package mx.bhit.omicron.app.restful.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.bhit.omicron.app.restful.model.Request;
import mx.bhit.omicron.app.restful.model.Response;
import mx.bhit.omicron.app.restful.model.SocialNetworksMonitoringConfProfResponse;
import mx.bhit.omicron.app.restful.service.ValidationService;
import mx.bhit.omicron.app.restful.thread.TwitterMonitorThread;

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
        logger.debug("controlador");
        String timeSlot = null;
        String expression = null;
        String kafkaTopic = null;
        String kafkaEndpoint = null;

        socialNetworksMonitoringConfProfResponse = new SocialNetworksMonitoringConfProfResponse();
        ValidationService validationService = new ValidationService();

        String msgError = null;
        // Obtenemos los parametros
        try {
            timeSlot = (String) request.getSocialNetworksMonitoringConfProf().get("timeSlot");
            expression = (String) request.getSocialNetworksMonitoringConfProf().get("keywordExpression");
            kafkaTopic = (String) request.getSocialNetworksMonitoringConfProf().get("kafkaTopic");
            kafkaEndpoint = (String) request.getSocialNetworksMonitoringConfProf().get("kafkaEndpoint");
        } catch (Exception e) {
            // TODO: handle exception
            msgError = "there is no parameter";
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
                "warning");
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
                msgError);
            e.printStackTrace();
            return socialNetworksMonitoringConfProfResponse;
        }

        Object[] parametros = { timeSlot, expression, kafkaTopic, kafkaEndpoint };

        if (validationService.validaInPar(parametros)) {
            msgError = "It needs some parameter";
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
                "warning");
            socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
                msgError);
            return socialNetworksMonitoringConfProfResponse;
        }

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

        /*
         * Se valida la expresion. Si no hay expresión no hay query.En caso
         * contrario se valida la Forma Normal Comun
         */
        Boolean query = (Objects.isNull(expression)) ? false : true;
        query = (expression.trim().length() == 0) ? false : true;
        if (query) {
            msgError = validationService.validaExpresion(expression);
            if (msgError != null) {
                socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
                    "error");
                socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
                    "msgError");
                return socialNetworksMonitoringConfProfResponse;
            }
        }

        // Si no hubo errores, se agrega la configuración del Thread
        trigger.addConfiguration(timeSlot);

        socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
            "success");
        socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
            "The monitor is up and running");

        TwitterMonitorThread twitterMonitorThread =
            new TwitterMonitorThread(query, expression, kafkaTopic, kafkaEndpoint);
        trigger.execute(twitterMonitorThread);

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
        trigger = null;
        msgError = "the monitor has stopped";
        socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("status",
            "success");
        socialNetworksMonitoringConfProfResponse.getSocialNetworksMonitoringConfProfResult().put("message",
            msgError);
        return socialNetworksMonitoringConfProfResponse;
    }

}

package mx.bhit.omicron.app.restful.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationService {
    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

    public ValidationService() {
        // TODO Auto-generated constructor stub
    }

    public String validaExpresion(String expression) {

        String msg = null;
        msg = Objects.isNull(expression) ? "keyword expression  cant be null" : null;

        return msg;
    }

    public String validaTiempo(String timeSlot) {
        System.out.println("validando tiempo");
        String msg = null;
        long aux;
        // Valida si viene null
        msg = Objects.isNull(timeSlot) ? "timeSlot cant be null" : null;
        // Valida si es un número
        try {
            aux = new Long(timeSlot);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return msg = "timeSlot incorrect format";
        }
        // Valida que sea positivo
        msg = (aux < 0) ? "timeSlot has to be a positive integer" : null;

        return msg;
    }

    // valida si es positivo
    public boolean validaMonitor(String[] params) {
        return true;
    }

}

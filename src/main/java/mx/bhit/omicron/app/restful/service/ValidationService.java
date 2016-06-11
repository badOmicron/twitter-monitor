package mx.bhit.omicron.app.restful.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO [Agregar documentación de la clase]
 * @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
 * @version 1.0
 * @since SIIRFE 6.0
 */
public class ValidationService {
    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public ValidationService() {
        // TODO Auto-generated constructor stub
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param parametros
     * @return
     */
    public boolean validaInPar(Object[] parametros) {
        boolean nulo = false;
        for (Object object : parametros) {
            nulo = Objects.isNull(object) ? true : false;
            if (nulo) {
                return nulo;
            }
        }
        return nulo;
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param expression
     * @return
     */
    public String validaExpresion(String expression) {

        String msg = null;
        // msg = Objects.isNull(expression) ? "keyword expression cant be null" : null;

        return msg;
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param timeSlot
     * @return
     */
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

}

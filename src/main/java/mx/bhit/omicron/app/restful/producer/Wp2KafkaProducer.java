package mx.bhit.omicron.app.restful.producer;

import java.nio.channels.ClosedChannelException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * TODO [Agregar documentación de la clase]
 * @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
 * @version 1.0
 * @since SIIRFE 6.0
 */
public class Wp2KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(Wp2KafkaProducer.class);

    private final kafka.javaapi.producer.Producer<Integer, String> producer;
    private final String topic;
    private final String kafkaEndpoint;
    private final Properties props = new Properties();

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param kafkaTopic
     * @param kafkaEndpoint
     */
    public Wp2KafkaProducer(String kafkaTopic, String kafkaEndpoint) {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", kafkaEndpoint);
        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
        this.topic = kafkaTopic;
        this.kafkaEndpoint = kafkaEndpoint;
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param content
     */
    public void writeToKafka(String content) throws ClosedChannelException {
        logger.debug("Escribiendo al kafkaTopic [" + topic + "]");
        String messageStr = new String(content);
        try {
            producer.send(new KeyedMessage<Integer, String>(this.topic, messageStr));
        } catch (Exception e) {
            // TODO: handle exception
            String msg = "Error al intentar consumir el servicio Kafka con ENDPONT: " + this.kafkaEndpoint
                + " causa : " + e.getMessage();
            logger.error(msg);
        }
    }
}

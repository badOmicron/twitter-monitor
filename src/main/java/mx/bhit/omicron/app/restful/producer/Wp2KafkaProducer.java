package mx.bhit.omicron.app.restful.producer;

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
    private final Properties props = new Properties();

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public Wp2KafkaProducer(String topic) {
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "localhost:9092");
        // Use random partitioner. Don't need the key type. Just set it to Integer.
        // The message is of type String.
        producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param topic
     * @param content
     */
    public void writeToKafka(String content) {
        logger.debug("Writing to kafkaTopic [" + topic + "]");
        String messageStr = new String(content);
        producer.send(new KeyedMessage<Integer, String>(this.topic, messageStr));
    }

    // public static void main(String[] args) {
    // Wp2KafkaProducer Wp2KafkaProducer = new Wp2KafkaProducer("twitter");
    // Wp2KafkaProducer.writeToKafka("twitter", "Orlando Ramos Galvan");
    // }
}

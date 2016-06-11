package eu.supersede.bdma.validation.simulator;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

public class Wp2KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(Wp2KafkaProducer.class);

    private KafkaProducer<String, String> producer;

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     */
    public Wp2KafkaProducer() {
        try {
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            props.put("metadata.broker.list", "localhost:9092");
            props.put("serializer.class", "kafka.serializer.StringEncoder");
            props.put("request.required.acks", "1");

            KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

            boolean sync = false;
            String topic = "mytopic";
            String key = "mykey";
            String value = "myvalue";
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, key, value);
            if (sync) {
                producer.send(producerRecord).get();
            } else {
                producer.send(producerRecord);
            }
            producer.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * TODO [Agregar documentacion al método]
     * @author @author Orlando Adrián Ramos Galván (orlando.ramos@ine.mx, orlandoa.ramos@outlook.com)
     * @param topic
     * @param content
     */
    public void writeToKafka(String topic, String content) {
        logger.debug("Writing to kafkaTopic [" + topic + "]");
        // logger.debug("Content [" + eu.supersede.bdma.validation.simulator.util.Util.cutString(content)
        // + "]");
        System.out.println("test");
        ProducerRecord<String, String> data = new ProducerRecord<String, String>(topic, content);
        logger.debug("Defined new ProducerRecord");
        long X = System.currentTimeMillis();
        this.producer.send(data, null);
        long Y = System.currentTimeMillis();
        logger.debug("Time to send " + (Y - X) + " ms");
        logger.debug("New event streamed to Kafka");
    }
}

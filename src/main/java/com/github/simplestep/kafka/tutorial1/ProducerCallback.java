package com.github.simplestep.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerCallback {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create Producer properties
        Logger logger = LoggerFactory.getLogger(ProducerCallback.class);
        String bootstrapServers = "localhost:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Send data
        for (int i = 0 ; i < 10; i++) {
            String topic = "first_topic";
            String value = "Hello " + i;
            String key = "id_" + i;
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(
                    topic, key, value
            );
            producer.send(producerRecord, (recordMetadata, e) -> {
                // execute everytime record is successfully sent or an exception is thrown
                if (e == null) {
                    logger.info("Received new metadata. \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp()
                    );
                } else {
                    logger.error("Error while producing: " + e);
                }
            }).get(); // Block the .send() to make it synchronous (should not do this on production)
        }

        // Flush and close producer
        producer.flush();
        producer.close();
    }
}

Kafka comes with serialization classes for simple types suchs as string, integers, and byte arrays. However, one has to use a serialization for complex types.
We can use JSON, Avro, Thrift or Protobuf for serializing and deserializing Kafka messages.

## Using Avro with Kafka
Avro provides robust support for schema evolution. Avro is especially suited for kafka as producers can switch to new schemas while allowing consumers to read records
conforming to both the old or the new schema. The beauty of Avro is that a reader doesn't need to know the record schema before reading avro file. The schema comes embedded in the
file. Since a Kafka topic can contain messages conforming to different Avro schemas, it necessitates that every message also holds its schema. 
This becomes impractical for Kafka, as including schema in every message leads to bloat in message size. Kakfa addresses this issue by introducing the Schema Registry, where actual schemas are stored. 
Kafka messages only contain an identifier to their schema in the registry. 
This complexity is abstracted away from the user, with the serializer and deserializer responsible for pushing and pulling the schema from the registry.

When sending a message to a topic *t*, the Avro schema for the key and the value will be automatically registered in Schema Registry under the subject t-key and t-value, respectively, 
if the compatibility test passes. The only exception is that the null type is never registered in Schema Registry.

```java
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.Properties;

Properties props = new Properties();
props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
          org.apache.kafka.common.serialization.StringSerializer.class);
props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
          io.confluent.kafka.serializers.KafkaAvroSerializer.class);
props.put("schema.registry.url", "http://localhost:8081");
KafkaProducer producer = new KafkaProducer(props);

String key = "key1";
String userSchema = "{\"type\":\"record\"," +
                    "\"name\":\"myrecord\"," +
                    "\"fields\":[{\"name\":\"f1\",\"type\":\"string\"}]}";
Schema.Parser parser = new Schema.Parser();
Schema schema = parser.parse(userSchema);
GenericRecord avroRecord = new GenericData.Record(schema);
avroRecord.put("f1", "value1");

ProducerRecord<Object, Object> record = new ProducerRecord<>("topic1", key, avroRecord);
try {
  producer.send(record);
} catch(SerializationException e) {
  // may need to do something with it
}
// When you're finished producing records, you can flush the producer to ensure it has all been written to Kafka and
// then close the producer to free its resources.
finally {
  producer.flush();
  producer.close();
}
```

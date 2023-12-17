package com.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

	//Long: It is the key and messages with same key will go into same partition.
	//Product: Value. It actually represents the whole record that would be appended to partition.
	
//	Kafka uses the abstraction of a distributed log that consists of partitions. Splitting a log into partitions allows to scale-out the system.
//
//	Keys are used to determine the partition within a log to which a message get's appended to. While the value is the actual payload of the message. The examples are actually not very "good" with this regard; usually you would have a complex type as value (like a tuple-type or a JSON or similar) and you would extract one field as key.
//
//	See: http://kafka.apache.org/intro#intro_topics and http://kafka.apache.org/intro#intro_producers
//
//	In general the key and/or value can be null, too. If the key is null a random partition will the selected. If the value is null it can have special "delete" semantics in case you enable log-compaction instead of log-retention policy for a topic
	
	@Autowired
	private KafkaTemplate<Long, Product> template;
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveProduct(@RequestBody Product product) {
//		System.out.println("SEND 1 : Sending "+product + " to topic quickstart-events-data1");
//		template.send("quickstart-events-data1", product);
		
		System.out.println("SEND 2 : Sending "+product + " to topic quickstart-events-data1");
		template.send("quickstart-events-data1", 4, product.getProductId(), product);
	}
	
	
}

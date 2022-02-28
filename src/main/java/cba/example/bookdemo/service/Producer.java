package cba.example.bookdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

	public static final String topic = "book_topic";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void publishToTopic(String message) {
		
		System.out.println("Publishing message to topic");
		kafkaTemplate.send(topic, message);
	}
}

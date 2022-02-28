package cba.example.bookdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cba.example.bookdemo.controller.BookdemoController;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Producer {

	public static final String topic = "book_topic";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void publishToTopic(String message) {
		
		log.info("Publishing message to topic");
		kafkaTemplate.send(topic, message);
	}
}

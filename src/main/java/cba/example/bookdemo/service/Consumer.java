package cba.example.bookdemo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	@KafkaListener(topics="book_topic", groupId="book_group")
	public void consumeFromTopic(String message) {
		System.out.println("Consumed message from topic - "+message);
	}
}

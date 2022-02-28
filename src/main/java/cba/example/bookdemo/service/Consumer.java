package cba.example.bookdemo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import cba.example.bookdemo.controller.BookdemoController;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {

	@KafkaListener(topics="book_topic", groupId="book_group")
	public void consumeFromTopic(String message) {
		log.info("Consumed message from topic - "+message);
	}
}

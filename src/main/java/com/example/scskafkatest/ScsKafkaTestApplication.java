package com.example.scskafkatest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableBinding({Channels.class})
@EnableSwagger2
@RestController
public class ScsKafkaTestApplication {

	private static final Logger logger = LoggerFactory.getLogger(ScsKafkaTestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ScsKafkaTestApplication.class, args);
	}

	@Autowired
	MessageChannel testOutput;

	@GetMapping("/testProducer")
	public void testProducer() {
		String payload = "test";
		Message<String> message = MessageBuilder.withPayload(payload).build();
		testOutput.send(message);
		logger.info("Message sent: {}", payload);
	}

	@StreamListener(value = "testInput")
	public void testConsumer(@Payload String msg) {
		logger.info("RECEIVED: {}", msg);
	}

}

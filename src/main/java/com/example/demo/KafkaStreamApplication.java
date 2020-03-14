package com.example.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.demo.producer.Producer;

@SpringBootApplication
public class KafkaStreamApplication  implements CommandLineRunner{
	@Autowired Producer producer;
	public static Logger logger = LoggerFactory.getLogger(KafkaStreamApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamApplication.class, args).close();
    }

   
    private final CountDownLatch latch = new CountDownLatch(3);

    public void run(String... args) throws Exception {
    	producer.sendMessage("Hello");
        latch.await(60, TimeUnit.SECONDS);
        
        logger.info("All sent");
    }

  

}

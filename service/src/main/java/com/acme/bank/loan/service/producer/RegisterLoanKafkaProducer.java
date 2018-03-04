package com.acme.bank.loan.service.producer;

import com.acme.bank.loan.domain.config.KafkaTopic;
import com.acme.bank.loan.domain.event.RegisterLoanEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RegisterLoanKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterLoanKafkaProducer.class);

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public RegisterLoanKafkaProducer(final KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(RegisterLoanEvent event) {
        String key = event.getEventId().toString();
        LOGGER.info("Sending event with key {} on topic {}", key, KafkaTopic.REGISTERED_LOANS.getTopicName());
        kafkaTemplate.send(KafkaTopic.REGISTERED_LOANS.getTopicName(), key, event);
    }
}

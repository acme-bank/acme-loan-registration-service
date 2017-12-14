package com.acme.bank.loan.registration.service.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.acme.bank.loan.registration.domain.config.AcmeProperties;
import com.acme.bank.loan.registration.domain.event.RegisterLoanEvent;

@Component
public class RegisterLoanKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterLoanKafkaProducer.class);

    private final AcmeProperties acmeProperties;
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public RegisterLoanKafkaProducer(final AcmeProperties acmeProperties,
                                     final KafkaTemplate<Object, Object> kafkaTemplate) {
        this.acmeProperties = acmeProperties;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(RegisterLoanEvent event) {
        AcmeProperties.Kafka.Topics topics = acmeProperties.getKafka().getTopics();

        String key = event.getUuid().toString();
        LOGGER.info("Sending event with key {} on topic {}", key, topics.getRegisterLoan());
        kafkaTemplate.send(topics.getRegisterLoan(), key, event);
    }
}

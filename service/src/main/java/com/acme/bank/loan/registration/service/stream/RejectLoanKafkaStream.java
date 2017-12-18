package com.acme.bank.loan.registration.service.stream;

import com.acme.bank.loan.registration.domain.config.AcmeProperties;
import com.acme.bank.loan.registration.domain.event.RejectLoanEvent;
import com.acme.bank.loan.registration.service.helper.KafkaHelper;
import com.acme.bank.loan.registration.service.service.RegisterLoanService;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

@SuppressWarnings({"Duplicates", "unchecked", "unused"})
@Component
public class RejectLoanKafkaStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(RejectLoanKafkaStream.class);

    private String applicationName;
    private final AcmeProperties acmeProperties;
    private KafkaStreams streams;
    private final KafkaHelper kafkaHelper;
    private final RegisterLoanService service;

    @Autowired
    public RejectLoanKafkaStream(@Value("${spring.application.name}") String applicationName,
                                 final AcmeProperties acmeProperties,
                                 final KafkaHelper kafkaHelper,
                                 final RegisterLoanService service) {
        this.applicationName = applicationName;
        this.acmeProperties = acmeProperties;
        this.kafkaHelper = kafkaHelper;
        this.service = service;
    }

    @PostConstruct
    public void startStream() {
        AcmeProperties.Kafka.Topics topics = acmeProperties.getKafka().getTopics();

        StreamsBuilder streamBuilder = new StreamsBuilder();
        streamBuilder.stream(topics.getRejectLoan(), kafkaHelper.consumedWith(RejectLoanEvent.class))
                .foreach(this::save);

        streams = new KafkaStreams(streamBuilder.build(), properties());
        streams.start();
    }

    private void save(String key, RejectLoanEvent event) {
        AcmeProperties.Kafka.Topics topics = acmeProperties.getKafka().getTopics();

        LOGGER.info("Received event with key {} on topic {}", key, topics.getRejectLoan());

        LOGGER.info("Saving event with key {} to database", key);

        service.save(event);
    }

    private Properties properties() {
        AcmeProperties.Kafka.Topics topics = acmeProperties.getKafka().getTopics();
        return kafkaHelper.properties(applicationName.concat("-").concat(topics.getRejectLoan()));
    }

    @PreDestroy
    public void closeStream() {
        if (streams != null) {
            streams.close();
        }
    }
}

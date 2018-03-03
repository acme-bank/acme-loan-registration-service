package com.acme.bank.loan.service.stream;

import com.acme.bank.loan.domain.config.AcmeProperties;
import com.acme.bank.loan.domain.config.KafkaTopic;
import com.acme.bank.loan.domain.event.RejectLoanEvent;
import com.acme.bank.loan.service.helper.KafkaHelper;
import com.acme.bank.loan.service.service.RegisterLoanService;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SuppressWarnings({"Duplicates", "unchecked", "unused"})
@Component
public class RejectLoanKafkaStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(RejectLoanKafkaStream.class);

    private final AcmeProperties acmeProperties;
    private KafkaStreams streams;
    private final KafkaHelper kafkaHelper;
    private final RegisterLoanService service;

    @Autowired
    public RejectLoanKafkaStream(final AcmeProperties acmeProperties,
                                 final KafkaHelper kafkaHelper,
                                 final RegisterLoanService service) {
        this.acmeProperties = acmeProperties;
        this.kafkaHelper = kafkaHelper;
        this.service = service;
    }

    @PostConstruct
    public void startStream() {
        StreamsBuilder streamBuilder = new StreamsBuilder();
        streamBuilder.stream(KafkaTopic.REJECTED_LOANS.getTopicName(), kafkaHelper.consumedWith(RejectLoanEvent.class))
                .foreach(this::save);

        streams = new KafkaStreams(streamBuilder.build(), acmeProperties.kafkaProperties(KafkaTopic.REJECTED_LOANS));
        streams.start();
    }

    private void save(String key, RejectLoanEvent event) {
        LOGGER.info("Received event with key {} on topic {}", key, KafkaTopic.REJECTED_LOANS.getTopicName());

        LOGGER.info("Saving event with key {} to database", key);

        service.save(event);
    }

    @PreDestroy
    public void closeStream() {
        if (streams != null) {
            streams.close();
        }
    }
}

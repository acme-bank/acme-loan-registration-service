package com.acme.bank.loan.registration.service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.acme.bank.loan.registration.domain.converter.UUIDAttributeConverter;
import com.acme.bank.loan.registration.domain.converter.ZonedDateTimeAttributeConverter;

@EnableJpaRepositories(basePackages = {"com.acme.bank.loan.registration.service.repository"})
@EntityScan(basePackageClasses = {
        Jsr310JpaConverters.class,
        ZonedDateTimeAttributeConverter.class,
        UUIDAttributeConverter.class},
        basePackages = {"com.acme.bank.loan.registration.domain.entity"})
@Configuration
public class DatabaseConfig {

}

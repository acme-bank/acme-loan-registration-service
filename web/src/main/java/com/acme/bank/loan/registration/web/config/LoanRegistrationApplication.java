package com.acme.bank.loan.registration.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
        "com.acme.bank.loan.registration.web.resource"
})
@SpringBootApplication
public class LoanRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanRegistrationApplication.class, args);
    }
}

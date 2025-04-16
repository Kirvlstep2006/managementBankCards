package com.step.managementbankcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.step.managementbankcard.repository")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ManagementBankCardApplication{
    public static void main(String[] args) {
        SpringApplication.run(ManagementBankCardApplication.class, args);
    }
}

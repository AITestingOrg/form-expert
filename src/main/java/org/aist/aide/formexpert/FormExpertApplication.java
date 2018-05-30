package org.aist.aide.formexpert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FormExpertApplication {
    public static void main(String[] args) {
        SpringApplication.run(FormExpertApplication.class, args);
    }
}

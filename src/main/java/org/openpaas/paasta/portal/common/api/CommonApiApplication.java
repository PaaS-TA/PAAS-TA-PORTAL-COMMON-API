package org.openpaas.paasta.portal.common.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"org.openpaas.paasta.portal.common.api"})
public class CommonApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApiApplication.class, args);
    }

}


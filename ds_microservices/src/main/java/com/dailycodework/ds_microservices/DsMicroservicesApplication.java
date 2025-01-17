package com.dailycodework.ds_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient

public class DsMicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DsMicroservicesApplication.class, args);
    }

}


package com.spring.microservices.dairyfactoryinventoryservice.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-service-discovery")
@EnableDiscoveryClient
@Configuration
public class LocalServiceDiscovery {
}

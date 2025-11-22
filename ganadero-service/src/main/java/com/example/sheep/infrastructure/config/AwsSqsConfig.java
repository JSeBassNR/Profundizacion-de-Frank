package com.example.sheep.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsSqsConfig {
 @Bean
 public SqsClient sqsClient() {
 return SqsClient.builder()
 .region(Region.of(System.getenv().getOrDefault("AWS_REGION", "us-east-1")))
 .credentialsProvider(DefaultCredentialsProvider.create())
 .build();
 }
}


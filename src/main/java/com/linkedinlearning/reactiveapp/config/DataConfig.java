package com.linkedinlearning.reactiveapp.config;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@Configuration
public class DataConfig extends AbstractReactiveMongoConfiguration {

  @Bean
  @Override
  public MongoClient reactiveMongoClient() {
    return MongoClients.create("mongodb://localhost:27017");
  }

  @Override
  protected String getDatabaseName() {
    return "reactive";
  }
}
package com.esgScore.server.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
  basePackages = "com.esgScore.server.repository.main",
  mongoTemplateRef = "mongoTemplate"
)
public class MainMongoConfig {

  @Value("${spring.data.mongodb.uri}")
  private String mainMongoUri;

  @Bean
  public MongoClient mainMongoClient() {
    return MongoClients.create(mainMongoUri);
  }

  @Primary
  @Bean
  public MongoDatabaseFactory mongoDbFactory() {
    return new SimpleMongoClientDatabaseFactory(mainMongoClient(), "ESG"); // DB 이름 명시
  }

  @Primary
  @Bean(name = "mongoTemplate")
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoDbFactory());
  }
}
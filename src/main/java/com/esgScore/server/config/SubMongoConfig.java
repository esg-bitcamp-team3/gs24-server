package com.esgScore.server.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
  basePackages = "com.esgScore.server.repository.sub", // 서브 DB용 Repository 패키지
  mongoTemplateRef = "subMongoTemplate"
)
public class SubMongoConfig {

  @Value("${spring.sub.data.mongodb.uri}")
  private String subMongoUri;

  @Bean
  public MongoClient subMongoClient() {
    return MongoClients.create(subMongoUri);
  }

  @Bean
  public MongoDatabaseFactory subMongoDbFactory() {
    return new SimpleMongoClientDatabaseFactory(subMongoClient(), "CompanyInfo"); // DB 이름 명시
  }

  @Bean(name = "subMongoTemplate")
  public MongoTemplate subMongoTemplate() {
    return new MongoTemplate(subMongoDbFactory());
  }
}
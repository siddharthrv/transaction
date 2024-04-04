package com.pismo.transaction.config;


import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class AppConfiguration {
  @Autowired
  public void FlywayConfiguration(DataSource dataSource) {
    Flyway.configure().dataSource(dataSource).baselineOnMigrate(true).load().migrate();
  }
}

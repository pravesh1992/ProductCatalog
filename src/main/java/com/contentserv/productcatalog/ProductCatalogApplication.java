package com.contentserv.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.contentserv.productcatalog")
@EntityScan(basePackages = "com.contentserv.productcatalog.repositores")
@ComponentScan(basePackages = "com.contentserv.productcatalog")
public class ProductCatalogApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductCatalogApplication.class, args);
  }
}
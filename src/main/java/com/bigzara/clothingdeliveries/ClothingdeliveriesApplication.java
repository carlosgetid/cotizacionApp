package com.bigzara.clothingdeliveries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.bigzara", lazyInit = true)
public class ClothingdeliveriesApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClothingdeliveriesApplication.class, args);
  }

}

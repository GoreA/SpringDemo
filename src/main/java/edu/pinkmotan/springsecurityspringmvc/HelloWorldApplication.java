package edu.pinkmotan.springsecurityspringmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

/**
 * @author GoreA
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude=ErrorMvcAutoConfiguration.class)
public class HelloWorldApplication {

  static {
    System.setProperty("error.whitelabel.enabled", "false");
  }

  public static void main(String[] args) {
    SpringApplication.run(HelloWorldApplication.class, args);
  }

}

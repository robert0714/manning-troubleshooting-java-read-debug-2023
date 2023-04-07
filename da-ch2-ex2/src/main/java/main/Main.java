package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import controllers.ProductController;

public class Main {

  public static void main(String[] args) {
    try (AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
      c.getBean(ProductController.class).saveProduct("Beer");
    }
  }

}

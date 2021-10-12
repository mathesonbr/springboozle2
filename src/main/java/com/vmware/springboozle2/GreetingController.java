package com.vmware.springboozle2;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
  private static final String template = "Hello, %s, from k8s pod %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World")  String name){
    String value = System.getenv("HOSTNAME");
    return new Greeting(counter.incrementAndGet(), String.format(template, name, value));
  }
}

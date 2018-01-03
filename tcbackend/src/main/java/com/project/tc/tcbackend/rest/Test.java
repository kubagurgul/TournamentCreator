package com.project.tc.tcbackend.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
  @RequestMapping("/")
  public String test() {
    System.out.println("ASDFSDFASDF");
    return "just for tests";
  }
}

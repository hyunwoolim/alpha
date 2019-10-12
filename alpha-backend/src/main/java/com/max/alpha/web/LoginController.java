package com.max.alpha.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @GetMapping("/api/auth/login")
  public void loginCallback(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state) {
    System.out.println(code);
  }
}

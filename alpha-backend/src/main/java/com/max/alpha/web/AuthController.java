package com.max.alpha.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @CrossOrigin(origins = "*")
  @GetMapping("/login-failed")
  public String loginFailed() {
    System.out.println("aaaaaa");
    return "/login?error=true";
  }

  @CrossOrigin(origins = "*")
  @GetMapping({"/login-succeeded", "/logout-succeeded"})
  public String success() {
    return "/";
  }

}

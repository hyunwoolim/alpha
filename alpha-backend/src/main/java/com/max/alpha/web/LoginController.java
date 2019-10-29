package com.max.alpha.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @CrossOrigin(origins = "http://localhost:4041")
  @GetMapping({"/login-succeeded", "logout-succeeded"})
  public String success() {
    return "/";
  }

}

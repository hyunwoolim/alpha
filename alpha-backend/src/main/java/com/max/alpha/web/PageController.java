package com.max.alpha.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
  @GetMapping(value = {
    "/",
    "/login", "/logout",
    "/my-info",
    "/friends"
  })
  public String index() {
    return "index";
  }
  /*@GetMapping("/login")
  public String login() {
    return "index";
  }*/
}

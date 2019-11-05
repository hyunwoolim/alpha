package com.max.alpha.web;

import com.max.alpha.model.Member;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

  @GetMapping(value = "/public/session")
  public static Member session() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth.getPrincipal() instanceof Member) {
      return (Member) auth.getPrincipal();
    }
    return null;
  }

}

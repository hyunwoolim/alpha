package com.max.alpha.web;

import com.google.common.base.Strings;
import com.max.alpha.model.Member;
import com.max.alpha.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

  @Autowired
  private MemberRepository memberRepository;

  @GetMapping(value = "/api/public/session")
  public Member session() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (!Strings.isNullOrEmpty(auth.getName())) {
      System.out.println(auth.getName());
    }
    return null;
  }

}

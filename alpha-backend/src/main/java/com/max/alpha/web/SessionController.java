package com.max.alpha.web;

import com.google.common.base.Strings;
import com.max.alpha.config.security.SessionUtil;
import com.max.alpha.model.Member;
import com.max.alpha.model.data.MemberData;
import com.max.alpha.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

  @Autowired
  private MemberService memberService;

  @GetMapping(value = "/public/session")
  public static Member session() {
    return SessionUtil.sessionMember();
  }

  @PostMapping(value = "/private/session")
  public void save(@RequestBody MemberData data) {
    if (data != null && !Strings.isNullOrEmpty(data.getId())) {
      memberService.save(data);
      // SessionUtil.refresh();
    }
  }


}

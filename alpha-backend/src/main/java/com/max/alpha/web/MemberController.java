package com.max.alpha.web;

import com.max.alpha.model.data.MemberData;
import com.max.alpha.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

  @Autowired
  private MemberService memberService;

  @PostMapping
  public void save(@RequestBody MemberData data) {
    memberService.save(data);
  }
}

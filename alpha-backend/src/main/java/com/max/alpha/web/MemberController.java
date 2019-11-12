package com.max.alpha.web;

import com.max.alpha.model.data.MemberData;
import com.max.alpha.service.MemberQuery;
import com.max.alpha.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private MemberQuery memberQuery;

  @PostMapping(value = "/public/member")
  public void create(@RequestBody MemberData data) {
    memberService.create(data);
  }

  @GetMapping(value = "/public/member/exists")
  public boolean exists(@RequestParam("mid") String mid) {
    return memberQuery.exist(mid);
  }

}

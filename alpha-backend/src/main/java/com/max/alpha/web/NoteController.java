package com.max.alpha.web;

import com.max.alpha.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

  @Autowired
  private MemberService memberService;

  @PostMapping(value = "/private/notes")
  public void findMyNotes() {
  }



}

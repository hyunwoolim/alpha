package com.max.alpha.service;

import com.max.alpha.model.Member;
import com.max.alpha.model.data.MemberData;
import com.max.alpha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private MemberRepository memberRepository;

  public void save(MemberData data) {
    if (!Strings.isNullOrEmpty(data.getPassword())) {
      data.setPassword(passwordEncoder.encode(data.getPassword()));
    }
    Member member = new Member().define(data);
    System.out.println(member);
    memberRepository.save(member);
  }

}

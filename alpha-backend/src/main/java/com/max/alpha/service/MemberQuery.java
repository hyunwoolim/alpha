package com.max.alpha.service;

import com.max.alpha.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberQuery {

  @Autowired
  private MemberRepository memberRepository;

  public boolean exist (String mid) {
    return memberRepository.findByMid(mid) != null;
  }

}

package com.max.alpha.service;

import com.google.common.base.Strings;
import com.max.alpha.config.security.PasswordEncoder;
import com.max.alpha.config.security.SessionUtil;
import com.max.alpha.model.Authority;
import com.max.alpha.model.Member;
import com.max.alpha.model.data.MemberData;
import com.max.alpha.model.enums.Role;
import com.max.alpha.repository.AuthorityRepository;
import com.max.alpha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private AuthorityRepository authorityRepository;

  public void create(MemberData data) {
    if (!Strings.isNullOrEmpty(data.getPassword())) {
      data.setPassword(passwordEncoder.encode(data.getPassword()));
    }
    Member member = new Member().define(data);
    memberRepository.save(member);
    authorityRepository.save(new Authority(member.getId(), Role.USER1, null));
  }


  public void save(MemberData data) {
    Optional<Member> memberOptional = memberRepository.findById(data.getId());
    Member member = memberOptional.isPresent() ? memberOptional.get().define(data) : null;
    if (member != null) {
      memberRepository.save(member);
      SessionUtil.refresh(member);
    }
  }

}

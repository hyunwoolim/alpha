package com.max.alpha.service;

import com.max.alpha.model.Member;
import com.max.alpha.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlphaUserDetailsService implements UserDetailsService {

  @Autowired
  private MemberRepository memberRepository;

  @Override
  public Member loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findByEmail(username);
    return member;
  }

}

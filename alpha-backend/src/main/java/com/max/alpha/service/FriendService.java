package com.max.alpha.service;

import com.google.common.base.Strings;
import com.max.alpha.config.security.SessionUtil;
import com.max.alpha.model.FriendRequest;
import com.max.alpha.model.Member;
import com.max.alpha.repository.FriendRepository;
import com.max.alpha.repository.FriendRequestRepository;
import com.max.alpha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private FriendRepository friendRepository;

  @Autowired
  private FriendRequestRepository friendRequestRepository;

  public void request(String toId) throws Exception {
    Member me = SessionUtil.sessionMember();
    if (me == null) {
      throw new Exception("no.session");
    }
    Member to = memberRepository.findByEmail(toId);
    if (to != null && !Strings.isNullOrEmpty(to.getId())) {
      friendRequestRepository.save(new FriendRequest(me.getId(), to.getId()));
    }
  }


}

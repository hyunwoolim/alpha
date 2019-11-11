package com.max.alpha.service;

import com.google.common.base.Strings;
import com.max.alpha.config.security.SessionUtil;
import com.max.alpha.model.Friend;
import com.max.alpha.model.FriendRequest;
import com.max.alpha.model.Member;
import com.max.alpha.model.data.FriendRequestData;
import com.max.alpha.repository.FriendRepository;
import com.max.alpha.repository.FriendRequestRepository;
import com.max.alpha.repository.MemberRepository;
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
    Member to = memberRepository.findByMid(toId);
    if (to == null) {
      return;
    }
    if (me.getId().equals(to.getId())) {
      throw new Exception("no.request.to.me");
    }
    if (to != null && !Strings.isNullOrEmpty(to.getId())) {
      if (friendRepository.findFriendRelationship(me.getId(), to.getId()) != null) {
        throw new Exception("already.friend");
      }
      friendRequestRepository.save(new FriendRequest(me.getId(), to.getId()));
    }
  }

  public void approve(FriendRequestData data) throws Exception {
    Member me = SessionUtil.sessionMember();
    if (me == null) {
      throw new Exception("no.session");
    }
    FriendRequest request = friendRequestRepository.findFriendRequests(data.getFromId(), me.getId());
    if (request == null) {
      throw new Exception("no.request");
    }
    if (friendRepository.findFriendRelationship(me.getId(), data.getFromId()) == null) {
      friendRepository.save(new Friend(me.getId(), data.getFromId()));
    }
    if (friendRepository.findFriendRelationship(data.getFromId(), me.getId()) == null) {
      friendRepository.save(new Friend(data.getFromId(), me.getId()));
    }
    friendRequestRepository.delete(request);
  }

  public void reject(FriendRequestData data) {

  }

}

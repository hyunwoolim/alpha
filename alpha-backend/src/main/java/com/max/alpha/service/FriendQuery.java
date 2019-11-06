package com.max.alpha.service;

import com.google.common.base.Strings;
import com.max.alpha.config.security.SessionUtil;
import com.max.alpha.model.Friend;
import com.max.alpha.model.FriendRequest;
import com.max.alpha.model.Member;
import com.max.alpha.repository.FriendRepository;
import com.max.alpha.repository.FriendRequestRepository;
import com.max.alpha.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FriendQuery {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private FriendRepository friendRepository;

  @Autowired
  private FriendRequestRepository friendRequestRepository;

  public List<Friend> findFriends () throws Exception {
    Member me = SessionUtil.sessionMember();
    if (me == null) {
      throw new Exception("no.session");
    }
    List<Friend> friends = friendRepository.findFriends(me.getId());
    List<Friend> results = new ArrayList<>();
    for (Friend friend : friends) {
      Optional<Member> friendOptional = memberRepository.findById(friend.getFriendId());
      if (friendOptional.isPresent()) {
        friend.setFriendMember(friendOptional.get());
        results.add(friend);
      }
    }
    return results;
  }

  public List<FriendRequest> findRequests () throws Exception {
    Member me = SessionUtil.sessionMember();
    if (me == null) {
      throw new Exception("no.session");
    }
    List<FriendRequest> requests = friendRequestRepository.findFriendRequests(me.getId());
    List<FriendRequest> results = new ArrayList<>();
    for (FriendRequest request : requests) {
      Optional<Member> fromMemberOptional = memberRepository.findById(request.getFromId());
      if (fromMemberOptional.isPresent()) {
        request.setFromMember(fromMemberOptional.get().secure());
        results.add(request);
      }
    }
    return results;
  }


}

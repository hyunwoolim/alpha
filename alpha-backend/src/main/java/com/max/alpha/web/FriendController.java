package com.max.alpha.web;

import com.max.alpha.model.Friend;
import com.max.alpha.model.FriendRequest;
import com.max.alpha.model.data.FriendRequestData;
import com.max.alpha.service.FriendQuery;
import com.max.alpha.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendController {

  @Autowired
  private FriendService friendService;

  @Autowired
  private FriendQuery friendQuery;

  @GetMapping(value = "/api/private/friends")
  public List<Friend> findFriends() throws Exception {
    return friendQuery.findFriends();
  }

  @PostMapping(value = "/api/private/friends/request")
  public void request(@RequestParam String toId) throws Exception {
    friendService.request(toId);
  }

  @PostMapping(value = "/api/private/friends/request/approve")
  public void approve(@RequestBody FriendRequestData data) throws Exception {
    friendService.approve(data);
  }

  @PostMapping(value = "/api/private/friends/request/reject")
  public void reject(@RequestBody FriendRequestData data) throws Exception {
    friendService.reject(data);
  }

  @GetMapping(value = "/api/private/friends/requests")
  public List<FriendRequest> requests() throws Exception {
    return friendQuery.findRequests();
  }
}

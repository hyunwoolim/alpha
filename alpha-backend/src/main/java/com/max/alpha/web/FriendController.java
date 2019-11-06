package com.max.alpha.web;

import com.max.alpha.model.FriendRequest;
import com.max.alpha.service.FriendQuery;
import com.max.alpha.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FriendController {

  @Autowired
  private FriendService friendService;

  @Autowired
  private FriendQuery friendQuery;

  @PostMapping(value = "/private/friend/request")
  public void request(@RequestParam String toId) throws Exception {
    System.out.println("toId");
    System.out.println(toId);
    friendService.request(toId);
  }

  @GetMapping(value = "/private/friend/requests")
  public List<FriendRequest> requests() throws Exception {
    return friendQuery.findRequests();
  }
}

package com.max.alpha.repository;

import com.max.alpha.model.FriendRequest;
import com.max.alpha.model.keys.FriendRequestKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, FriendRequestKey> {

  @Query("SELECT r FROM FriendRequest r WHERE r.toId = :id")
  List<FriendRequest> findFriendRequests(@Param("id") String id);

}

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

  @Query("SELECT r FROM FriendRequest r WHERE r.fromId = :id")
  List<FriendRequest> findMyRequests(@Param("id") String id);

  @Query("SELECT r FROM FriendRequest r WHERE r.fromId = :fromId AND r.toId = :id")
  FriendRequest findFriendRequests(@Param("fromId") String fromId, @Param("id") String id);

}

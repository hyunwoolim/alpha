package com.max.alpha.repository;

import com.max.alpha.model.Friend;
import com.max.alpha.model.keys.FriendKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, FriendKey> {

  @Query("SELECT f FROM Friend f WHERE f.id = :id")
  List<Friend> findFriends(@Param("id") String id);

  @Query("SELECT f FROM Friend f WHERE f.id = :id AND f.friendId = :friendId")
  Friend findFriendRelationship(@Param("id") String id, @Param("friendId") String friendId);

}

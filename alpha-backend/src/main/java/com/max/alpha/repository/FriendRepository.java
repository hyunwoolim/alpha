package com.max.alpha.repository;

import com.max.alpha.model.Friend;
import com.max.alpha.model.keys.FriendKey;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, FriendKey> {
}

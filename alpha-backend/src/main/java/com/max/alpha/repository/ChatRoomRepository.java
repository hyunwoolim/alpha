package com.max.alpha.repository;

import com.max.alpha.model.ChatRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, String> {

  @Query("SELECT c FROM ChatRoom c WHERE (c.host = :host AND c.guest = :guest) OR (c.host = :guest AND c.guest = :host)")
  ChatRoom findChatRoomByMembers(@Param("host") String host, @Param("guest") String guest);

  @Query("SELECT c FROM ChatRoom c WHERE c.host = :id OR c.guest = :id")
  List<ChatRoom> findChatRooms(@Param("id") String id);

}

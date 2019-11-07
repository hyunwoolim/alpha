package com.max.alpha.repository;

import com.max.alpha.model.ChatRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, String> {

  /*@Query("SELECT c FROM ChatRoom c WHERE ")
  ChatRoom findChatRoomByMembers(@Param("host") String host, @Param("guest") String guest);
  */

  @Query("SELECT c FROM ChatRoom c WHERE c.host = :host")
  List<ChatRoom> findMyChatRooms(@Param("host") String host);

  @Query("SELECT c FROM ChatRoom c WHERE c.roomId = :roomId")
  List<ChatRoom> findChatRoomsByRoomId(@Param("roomId") String roomId);

}

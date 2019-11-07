package com.max.alpha.web;

import com.max.alpha.model.ChatRoom;
import com.max.alpha.model.data.FriendData;
import com.max.alpha.model.enums.ChatRoomType;
import com.max.alpha.service.ChatQuery;
import com.max.alpha.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

  @Autowired
  private ChatQuery chatQuery;

  @Autowired
  private ChatService chatService;

  @PostMapping(value = "/private/chat/start")
  public ChatRoom chat(@RequestBody FriendData data) {
    ChatRoom chatRoom = chatQuery.findPrivateChatRoom(data);
    if (chatRoom == null) {
      chatRoom = chatService.createPrivateChatRoom(data);
    }
    return chatRoom;
  }


  @GetMapping(value = "/private/chats")
  public List<ChatRoom> findChats() throws Exception {
    List<ChatRoom> chatRoom = chatQuery.findChatRooms();
    return chatRoom;
  }

}

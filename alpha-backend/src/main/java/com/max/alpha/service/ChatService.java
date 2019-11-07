package com.max.alpha.service;

import com.max.alpha.model.ChatRoom;
import com.max.alpha.model.data.FriendData;
import com.max.alpha.model.enums.ChatRoomType;
import com.max.alpha.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;

@Service
@Transactional
public class ChatService {

  @Autowired
  private ChatRoomRepository chatRoomRepository;

  public ChatRoom createPrivateChatRoom(FriendData data) {
    String roomId = UUID.randomUUID().toString();
    ChatRoom c1 = new ChatRoom(data.getId(), roomId, ChatRoomType.PRIVATE);
    ChatRoom c2 = new ChatRoom(data.getFriendId(), roomId, ChatRoomType.PRIVATE);
    chatRoomRepository.saveAll(Arrays.asList(c1, c2));
    return c1;
  }
}

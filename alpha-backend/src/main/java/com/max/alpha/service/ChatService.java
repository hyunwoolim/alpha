package com.max.alpha.service;

import com.max.alpha.model.ChatRoom;
import com.max.alpha.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatService {

  @Autowired
  private ChatRoomRepository chatRoomRepository;

  public ChatRoom create(String host, String guest) {
    return chatRoomRepository.save(new ChatRoom(host, guest));
  }
}

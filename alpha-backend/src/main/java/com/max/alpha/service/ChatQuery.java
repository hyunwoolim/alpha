package com.max.alpha.service;

import com.max.alpha.config.security.SessionUtil;
import com.max.alpha.model.ChatRoom;
import com.max.alpha.model.Member;
import com.max.alpha.repository.ChatRoomRepository;
import com.max.alpha.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ChatQuery {

  @Autowired
  private ChatRoomRepository chatRoomRepository;

  @Autowired
  private MemberRepository memberRepository;

  public List<ChatRoom> findChatRooms() throws Exception {
    Member me = SessionUtil.sessionMember();
    if (me == null) {
      throw new Exception("no.session");
    }
    List<ChatRoom> chatRooms = chatRoomRepository.findChatRooms(me.getId());
    List<ChatRoom> results = new ArrayList<>();
    for (ChatRoom chatRoom : chatRooms) {
      chatRoom.setHostMember(chatRoom.getHost().equals(me.getId()) ? memberRepository.findById(me.getId()).get() : memberRepository.findById(chatRoom.getGuest()).get());
      results.add(chatRoom);
    }
    return results;
  }

  public ChatRoom findChatRoom(String host, String guest) {
    return chatRoomRepository.findChatRoomByMembers(host, guest);
  }

}

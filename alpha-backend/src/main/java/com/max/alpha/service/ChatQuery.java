package com.max.alpha.service;

import com.max.alpha.config.security.SessionUtil;
import com.max.alpha.model.*;
import com.max.alpha.model.data.FriendData;
import com.max.alpha.model.enums.ChatRoomType;
import com.max.alpha.repository.ChatRoomRepository;
import com.max.alpha.repository.MemberRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ChatQuery {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private ChatRoomRepository chatRoomRepository;

  @Autowired
  private MemberRepository memberRepository;

  public List<ChatRoom> findChatRooms() throws Exception {
    Member me = SessionUtil.sessionMember();
    if (me == null) {
      throw new Exception("no.session");
    }
    /*List<ChatRoom> chatRooms = chatRoomRepository.findChatRooms(me.getId());
    List<ChatRoom> results = new ArrayList<>();
    for (ChatRoom chatRoom : chatRooms) {
      chatRoom.setHostMember(chatRoom.getHost().equals(me.getId()) ? memberRepository.findById(me.getId()).get() : memberRepository.findById(chatRoom.getGuest()).get());
      results.add(chatRoom);
    }
    return results;*/
    return null;
  }

  public ChatRoom findPrivateChatRoom(FriendData data) {
    JPAQuery<ChatRoom> query = new JPAQuery<>(entityManager);
    QChatRoom chatRoom = QChatRoom.chatRoom;
    query.select(chatRoom)
      .from(chatRoom)
      .where(chatRoom.type.eq(ChatRoomType.PRIVATE)
        .and(chatRoom.host.eq(data.getId()).or(chatRoom.host.eq(data.getFriendId()))))
      .groupBy(chatRoom.roomId)
      .having(chatRoom.count().eq(2L));
    return query.fetchOne();
  }

}

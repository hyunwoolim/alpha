package com.max.alpha.model;

import com.max.alpha.model.enums.ChatRoomType;
import com.max.alpha.model.keys.ChatRoomKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "APM_CHAT_ROOM")
@IdClass(ChatRoomKey.class)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

  public ChatRoom (String host, String roomId, ChatRoomType type) {
    this.host = host;
    this.roomId = roomId;
    this.type = type;
  }

  @Id
  @NonNull
  @Column(name = "HOST", length = 36)
  private String host;

  @Id
  @NonNull
  @Column(name = "ROOM_ID", length = 36)
  private String roomId;

  @Id
  @NonNull
  @Column(name = "TYPE")
  @Enumerated(EnumType.STRING)
  private ChatRoomType type;

  @CreatedDate
  @Column(name = "CREATED_DATE", updatable = false)
  private Date createdDate;

  @Transient
  private Member hostMember;

}

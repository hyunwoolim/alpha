package com.max.alpha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "APM_CHAT_ROOM")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
public class ChatRoom {

  public ChatRoom () {
    this.id = UUID.randomUUID().toString();
  }

  public ChatRoom(String host, String guest) {
    this.id = UUID.randomUUID().toString();
    this.host = host;
    this.guest = guest;
  }

  @Id
  @NonNull
  @Column(name = "ID", length = 36)
  private String id;

  @NonNull
  @Column(name = "HOST")
  private String host;

  @NonNull
  @Column(name = "GUEST")
  private String guest;

  @CreatedDate
  @Column(name = "CREATED_DATE", updatable = false)
  private Date createdDate;

  @Transient
  private Member hostMember;

  @Transient
  private Member guestMember;

}

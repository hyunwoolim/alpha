package com.max.alpha.model;

import com.max.alpha.model.keys.FriendKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "APM_FRIEND")
@IdClass(FriendKey.class)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

  public Friend (String id, String friendId) {
    this.id = id;
    this.friendId = friendId;
  }

  @Id
  @NonNull
  @Column(name = "ID", length = 36)
  private String id;

  @Id
  @NonNull
  @Column(name = "FRIEND_ID")
  private String friendId;

  @CreatedDate
  @Column(name = "RELATION_DATE", updatable = false)
  private Date relationDate;

  @Transient
  private Member friendMember;

}

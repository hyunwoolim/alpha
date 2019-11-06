package com.max.alpha.model;

import com.max.alpha.model.keys.FriendRequestKey;
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
@Table(name = "APM_FRIEND_REQUEST")
@IdClass(FriendRequestKey.class)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {

  public FriendRequest(String fromId, String to) {
    this.fromId = fromId;
    this.toId = to;
  }

  @Id
  @NonNull
  @Column(name = "FROM_ID", length = 36)
  private String fromId;

  @Id
  @NonNull
  @Column(name = "TO_ID", length = 36)
  private String toId;

  @CreatedDate
  @Column(name = "REQUEST_DATE", updatable = false)
  private Date requestDate;

  @Transient
  Member fromMember;

  @Transient
  Member toMember;

}

package com.max.alpha.model;

import com.max.alpha.model.enums.BoardType;
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

@Data
@Entity
@Table(name = "APM_BOARD")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Board {

  @Id
  @NonNull
  @Column(name = "ID", length = 36)
  private String id;

  @NonNull
  @Column(name = "ROOM_ID", length = 36)
  private String roomId;

  @NonNull
  @Column(name = "TYPE")
  @Enumerated(EnumType.STRING)
  private BoardType type;

  @CreatedDate
  @Column(name = "CREATED_DATE", updatable = false)
  private Date createdDate;

  @Transient
  private Member hostMember;

}

package com.max.alpha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "APM_FRIEND")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
public class Friend {

  @Id
  @NonNull
  @Column(name = "ID", length = 36)
  private String id;

  @NonNull
  @Column(name = "HOST")
  private String host;

  @NonNull
  @Column(name = "NAME")
  private String name;

  @CreatedDate
  @Column(name = "CREATED_DATE", updatable = false)
  private Date createdDate;

}

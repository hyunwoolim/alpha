package com.max.alpha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "APM_AUTHORITY")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {
/*
  public Authority(String username, String authority) {
    this.username = username;
    this.authority = authority;
  }*/

  @Id
  @NonNull
  @Column(name = "USERNAME", length = 36)
  private String username;

  @NonNull
  @Column(name = "AUTHORITY")
  private String authority;

  @CreatedDate
  @Column(name = "CREATED_DATE", insertable = true, updatable = false)
  private Date createdDate;

}

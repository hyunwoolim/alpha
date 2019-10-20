package com.max.alpha.model;

import com.max.alpha.config.security.PasswordEncoder;
import com.max.alpha.model.data.MemberData;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "APM_MEMBER")
@EntityListeners(AuditingEntityListener.class)
public class Member {

  @Id
  @Column(name = "ID", length = 36)
  @NonNull
  private String id;

  @Column(name = "EMAIL")
  @NonNull
  private String email;

  @Column(name = "$PASSWORD$")
  @NonNull
  private String password;

  @Column(name = "NAME")
  @NonNull
  private String name;

  @CreatedDate
  @Column(name = "CREATED_DATE", insertable = true, updatable = false)
  private Date createdDate;

  public Member() {
    this.id = UUID.randomUUID().toString();
  }

  public Member define(MemberData data) {
    this.email = data.getEmail();
    this.name = data.getName();
    this.password = data.getPassword();
    return this;
  }
}

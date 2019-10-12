package com.max.alpha.model;

import com.max.alpha.model.data.MemberData;
import lombok.Data;
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
  private String id;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "$PASSWORD$")
  private String password;

  @Column(name = "NAME")
  private String name;

  @CreatedDate
  @Column(name = "CREATED_DATE", insertable = true, updatable = false)
  private Date createdDate;

  public Member() {
    this.id = UUID.randomUUID().toString();
  }

  public void define(MemberData data) {
    this.email = data.getEmail();
    this.name = data.getName();
    this.password = data.getPassword();
  }
}

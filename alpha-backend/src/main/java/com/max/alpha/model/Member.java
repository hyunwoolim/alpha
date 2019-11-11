package com.max.alpha.model;

import com.max.alpha.model.data.MemberData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "APM_MEMBER")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
public class Member implements UserDetails {

  @Id
  @Column(name = "ID", length = 36)
  private String id;

  @Column(name = "MID", unique = true)
  private String mid;

  @Column(name = "$PASSWORD$")
  private String password;

  @Column(name = "NAME")
  private String name;

  @Column(name = "PROFILE_IMAGE")
  private String profileImage;

  @Column(name = "IS_PRIVATE")
  private boolean isPrivate;

  @CreatedDate
  @Column(name = "CREATED_DATE", updatable = false)
  private Date createdDate;

  @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "USERNAME")
  private Collection<Authority> authorities;

  public Member() {
    this.id = UUID.randomUUID().toString();
  }

  public Member define(MemberData data) {
    this.mid = data.getMid();
    this.name = data.getName();
    this.password = data.getPassword();
    return this;
  }

  public Member secure() {
    this.password = null;
    this.authorities = null;
    this.createdDate = null;
    return this;
  }

  public void userAuthority(Authority authority) {
    this.authorities = Arrays.asList(authority);
  }


  @Override
  public String getUsername() {
    return this.mid;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

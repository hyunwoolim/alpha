package com.max.alpha.model;

import com.max.alpha.config.security.PasswordEncoder;
import com.max.alpha.model.data.MemberData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
  @NonNull
  @Column(name = "ID", length = 36)
  private String id;

  @NonNull
  @Column(name = "EMAIL")
  private String email;

  @NonNull
  @Column(name = "$PASSWORD$")
  private String password;

  @NonNull
  @Column(name = "NAME")
  private String name;

  @Column(name = "PROFILE_IMAGE")
  private String profileImage;

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
    this.email = data.getEmail();
    this.name = data.getName();
    this.password = data.getPassword();
    return this;
  }

  public void userAuthority(Authority authority) {
    this.authorities = Arrays.asList(authority);
  }


  @Override
  public String getUsername() {
    return this.email;
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

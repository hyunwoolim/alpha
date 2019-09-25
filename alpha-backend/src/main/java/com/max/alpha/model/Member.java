package com.max.alpha.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "APM_MEMBER")
public class Member {

  @Id
  @Column(name = "ID")
  private String id;

  @Column(name = "EMAIL")
  private String email;

}

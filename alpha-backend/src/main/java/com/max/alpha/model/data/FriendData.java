package com.max.alpha.model.data;

import lombok.Data;

import java.util.Date;

@Data
public class FriendData {
  private String id;
  private String friendId;
  private Date relationDate;
  private MemberData friendMember;
}

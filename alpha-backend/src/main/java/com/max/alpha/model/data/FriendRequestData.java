package com.max.alpha.model.data;

import lombok.Data;

import java.util.Date;

@Data
public class FriendRequestData {
  private String fromId;
  private String toId;
  private Date requestDate;
  private MemberData fromMember;
  private MemberData toMember;
}

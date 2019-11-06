package com.max.alpha.model.keys;

import lombok.Data;

import java.io.Serializable;

@Data
public class FriendRequestKey implements Serializable {

  private String fromId;

  private String toId;

}

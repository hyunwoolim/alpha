package com.max.alpha.model.keys;

import lombok.Data;

import java.io.Serializable;

@Data
public class FriendKey implements Serializable {

  private String from;

  private String to;

}

package com.max.alpha.model.keys;

import com.max.alpha.model.enums.ChatRoomType;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChatRoomKey implements Serializable {
  private String host;
  private String roomId;
  private ChatRoomType type;
}

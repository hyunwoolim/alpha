package com.max.alpha.model.data;

import lombok.Data;

import java.util.Date;

@Data
public class VideoData {
  private String id;
  private String title;
  private Date createdDate;
}

package com.max.alpha.model;

import com.max.alpha.model.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "APM_VIDEO")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Video {

  @Id
  @NonNull
  @Column(name = "ID", length = 36)
  private String id;

  @NonNull
  @Column(name = "TITLE")
  private String title;

  @NonNull
  @Column(name = "FILE_PATH")
  private String filePath;

  @Column(name = "MIME_TYPE")
  private String mimeType;

  @CreatedDate
  @Column(name = "CREATED_DATE", updatable = false)
  private Date createdDate;

}

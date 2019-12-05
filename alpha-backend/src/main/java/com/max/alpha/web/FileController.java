package com.max.alpha.web;

import com.google.common.base.Strings;
import com.max.alpha.model.data.VideoData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileController {

  @Value("${alpha.file.basedir}")
  private String fileBaseDir;

  @GetMapping("/api/video/download/{fileName:.+}")
  public ResponseEntity<Resource> videoDownload (@PathVariable String fileName) throws Exception {
    String path = this.fileBaseDir + "/" + fileName;
    Resource resource = new FileUrlResource(path);
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType("video/mp4"))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

  @PostMapping("/api/video/upload")
  public void videoUpload (@RequestPart(value = "file") final MultipartFile file) throws Exception {
    if (file == null && Strings.isNullOrEmpty(file.getOriginalFilename())) {
      throw new Exception("file.not.exists");
    }
    String uuid = UUID.randomUUID().toString();
    String originalFileName = file.getOriginalFilename();
    String extension = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
    String savingFileName = sdf.format(new Date()) + uuid + extension;
    final byte[] bytes = file.getBytes();
    final Path path = Paths.get(this.fileBaseDir + "/" + savingFileName);
    Files.write(path, bytes);
    String mimeType = Files.probeContentType(path);
    VideoData data = new VideoData();
  }

  private String mediaTypePath() {
    return null;
  }


}

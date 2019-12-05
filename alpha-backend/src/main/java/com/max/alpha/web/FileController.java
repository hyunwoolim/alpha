package com.max.alpha.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class FileController {

  @Value("${alpha.file.basedir}")
  private String fileBaseDir;

  @GetMapping("/api/download/{fileName:.+}")
  public ResponseEntity<Resource> download (@PathVariable String fileName) throws Exception {
    String path = this.fileBaseDir + "/" + fileName;
    Resource resource = new FileUrlResource(path);
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType("video/mp4"))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

  @PostMapping("/api/upload")
  public void upload (@RequestPart(value = "file") final MultipartFile file) throws Exception {
    final byte[] bytes = file.getBytes();
    final Path path = Paths.get(this.fileBaseDir + "/" + UUID.randomUUID().toString());
    Files.write(path, bytes);
  }


}

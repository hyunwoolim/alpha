package com.max.alpha.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
public class FileController {

  @Value("${alpha.file.basedir}")
  private String fileBaseDir;

  @GetMapping("/download/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile (@PathVariable String fileName) throws Exception {
    String path = this.fileBaseDir + fileName;
    Resource resource = new FileUrlResource(path);
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType("video/mp4"))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

}

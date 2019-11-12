package com.max.alpha.web;

import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.IDriveItemRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

  @GetMapping(value = "/api/public/file")
  public String test() {

    /*IGraphServiceClient graphClient =
      GraphServiceClient
        .builder()
        .authenticationProvider(authenticationProvider)
        .buildClient();*/
    return null;
  }

}

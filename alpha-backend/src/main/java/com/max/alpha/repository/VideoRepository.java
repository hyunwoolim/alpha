package com.max.alpha.repository;

import com.max.alpha.model.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, String> {
}

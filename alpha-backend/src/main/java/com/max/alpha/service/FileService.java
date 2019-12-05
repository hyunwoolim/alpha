package com.max.alpha.service;

import com.google.common.base.Strings;
import com.max.alpha.config.security.SessionUtil;
import com.max.alpha.model.Friend;
import com.max.alpha.model.FriendRequest;
import com.max.alpha.model.Member;
import com.max.alpha.model.data.FriendRequestData;
import com.max.alpha.repository.FriendRepository;
import com.max.alpha.repository.FriendRequestRepository;
import com.max.alpha.repository.MemberRepository;
import com.max.alpha.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
@Transactional
public class FileService {

  @Autowired
  private VideoRepository videoRepository;

  public void saveVideo () {

  }

}

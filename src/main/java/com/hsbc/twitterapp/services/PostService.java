package com.hsbc.twitterapp.services;

import com.hsbc.twitterapp.dto.UserIdDto;
import com.hsbc.twitterapp.entities.Message;

import java.util.List;

public interface PostService {

    List<Message> getAllWallPosts(UserIdDto postDto);
}

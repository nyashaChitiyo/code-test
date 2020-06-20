package com.hsbc.twitterapp.services;

import com.hsbc.twitterapp.dto.FollowUserDto;
import com.hsbc.twitterapp.dto.FollowerWallDto;
import com.hsbc.twitterapp.dto.Response;
import com.hsbc.twitterapp.dto.UserIdDto;
import com.hsbc.twitterapp.entities.Followers;
import com.hsbc.twitterapp.entities.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FollowerService {

    ResponseEntity<Response> followUser(FollowUserDto idDto);
    ResponseEntity<List<Message>>  listUsers(FollowerWallDto wallDto);
    Followers getFollowers(long userId);
}

package com.hsbc.twitterapp.controllers;

import com.hsbc.twitterapp.dto.FollowUserDto;
import com.hsbc.twitterapp.dto.FollowerWallDto;
import com.hsbc.twitterapp.dto.Response;

import com.hsbc.twitterapp.entities.Message;
import com.hsbc.twitterapp.services.FollowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/follow")
@Slf4j
@Api(value = "FollowerControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @ApiOperation("controller to follow another user by using the users ID's ")
    @PostMapping("/user")
    public ResponseEntity<Response> getUserWallPosts(@RequestBody FollowUserDto postDto){
        log.info("post dto {}",postDto);
        return followerService.followUser(postDto);
    }

    @ApiOperation("controller to get all the posts from the users that the user follows by using userId")
    @PostMapping("/get/posts")
    public ResponseEntity<List<Message>> getFollowersPosts(@RequestBody FollowerWallDto userDto){
        return followerService.listUsers(userDto);
    }
}

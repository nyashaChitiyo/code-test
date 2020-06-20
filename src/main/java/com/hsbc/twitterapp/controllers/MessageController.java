package com.hsbc.twitterapp.controllers;

import com.hsbc.twitterapp.dto.UserIdDto;
import com.hsbc.twitterapp.entities.Message;
import com.hsbc.twitterapp.model.MessageDto;
import com.hsbc.twitterapp.services.MessageService;
import com.hsbc.twitterapp.services.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wall")
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private PostService postService;

    @ApiOperation("controller to post a message and create user if user does not exist")
    @PostMapping("/post")
    public String postMessage(@RequestBody MessageDto messageDto){
        log.info("post dto {}",messageDto);
        return messageService.postMessage(messageDto);
    }

    @ApiOperation("controller to view all users post on timeline")
    @PostMapping("/my/posts")
    public List<Message> getUserWallPosts(@RequestBody UserIdDto postDto){
        log.info("post dto {}",postDto);
        return postService.getAllWallPosts(postDto);
    }
}

package com.hsbc.twitterapp.services.implementation;

import com.hsbc.twitterapp.dto.FollowUserDto;
import com.hsbc.twitterapp.dto.FollowerWallDto;
import com.hsbc.twitterapp.dto.Response;
import com.hsbc.twitterapp.entities.Followers;
import com.hsbc.twitterapp.entities.Message;
import com.hsbc.twitterapp.entities.User;
import com.hsbc.twitterapp.exceptions.NotFoundException;
import com.hsbc.twitterapp.repositories.FollowersRepository;
import com.hsbc.twitterapp.services.FollowerService;
import com.hsbc.twitterapp.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowersRepository followersRepository;

    @Autowired
    private MessageService messageService;

    @Override
    public ResponseEntity<Response> followUser(FollowUserDto idDto) {
        long userId = idDto.getUserId();
        long followerId = idDto.getFollowerId();
        messageService.findUserById(followerId);

        Followers followers = new Followers();
        followers.setFollowerId(followerId);
        User user = messageService.findUserById(userId);
        followers.setUsers(user);
        followersRepository.save(followers);
        Response response = new Response("successfully followed user "+userId);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Message>> listUsers(FollowerWallDto wallDto) {
        long userId = wallDto.getUserId();
        User user = messageService.findUserById(userId);
        List<Message> messages = getPosts(user);
        return new ResponseEntity<>(messages,HttpStatus.OK);
    }

    @Override
    public Followers getFollowers(long userId) {
        return followersRepository.findFollowersByFollowerId(userId).orElseThrow(()-> new NotFoundException("User with id "+userId+" not found"));
    }

    private List<Message> getPosts(User user){

        List<Followers> followers = user.getFollowers();
        log.info("followers are {}",followers);

        List<Long> collect = followers.stream().mapToLong(p -> p.getFollowerId()).boxed().collect(Collectors.toList());
        List<Message> messages = new ArrayList<>();

        collect.stream().forEach((p)->{
            messages.addAll(messageService.findUserById(p).getMessageList());
        });

        log.info(""+messages);
        messages.sort(Comparator.comparing(Message::getCreationDate));
        log.info(""+messages);
        return messages;
    }
}

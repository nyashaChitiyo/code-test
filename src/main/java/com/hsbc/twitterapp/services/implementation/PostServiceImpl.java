package com.hsbc.twitterapp.services.implementation;

import com.hsbc.twitterapp.dto.UserIdDto;
import com.hsbc.twitterapp.entities.Message;
import com.hsbc.twitterapp.entities.User;
import com.hsbc.twitterapp.repositories.UserRepository;
import com.hsbc.twitterapp.services.MessageService;
import com.hsbc.twitterapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private MessageService messageService;

    @Override
    public List<Message> getAllWallPosts(UserIdDto postDto) {
        long userId = postDto.getUserId();
        User user = messageService.findUserById(userId);
        List<Message> messages = user.getMessageList();
        return messages;
    }


}

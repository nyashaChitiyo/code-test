package com.hsbc.twitterapp.services.implementation;

import com.hsbc.twitterapp.entities.Message;
import com.hsbc.twitterapp.entities.User;
import com.hsbc.twitterapp.exceptions.BadRequestException;
import com.hsbc.twitterapp.exceptions.NotFoundException;
import com.hsbc.twitterapp.model.MessageDto;
import com.hsbc.twitterapp.repositories.MessageRepository;
import com.hsbc.twitterapp.repositories.UserRepository;
import com.hsbc.twitterapp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public String postMessage(MessageDto messageDto) {
        String msg = Optional.ofNullable(messageDto.getMessage()).orElseThrow(()-> new BadRequestException("Response cannot be null"));

        if(msg.length() > 140){
            throw new BadRequestException("Response Limit cannot exceed 140 characters");
        }

        long id = messageDto.getId();
        if(id == 0){

            long userId = generateId();
            User user = new User();
            user.setUserId(userId);
            userRepository.save(user);

            createUser(msg,userId);
            return "Your new username is "+userId;
        }
        else {
            createUser(msg,id);
            return "Message post successful";
        }

    }

    @Override
    public long generateId() {
        long index = ThreadLocalRandom.current().ints(100000, 999999).distinct().limit(1).findAny().getAsInt();
        return index;
    }

    @Override
    public User createUser(String message, long id) {

        User user = findUserById(id);

        createMessage(message,user);

        return null;
    }

    @Override
    public Message createMessage(String message, User user) {
        Message msg = new Message();
        msg.setMessage(message);
        msg.setUserNumber(user.getUserId());
        msg.setUser(user);
        messageRepository.save(msg);
        return msg;
    }

    @Override
    public User findUserById(long id) {
        String msg = "User with ID "+id+" does not exist";
        User user = userRepository.findUserByUserId(id).orElseThrow(()-> new NotFoundException(msg));
        return user;
    }

}

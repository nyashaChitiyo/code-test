package com.hsbc.twitterapp.services;

import com.hsbc.twitterapp.entities.Message;
import com.hsbc.twitterapp.entities.User;
import com.hsbc.twitterapp.model.MessageDto;
import java.util.List;

public interface MessageService {

    String postMessage(MessageDto messageDto);
    long generateId();
    User createUser(String message, long id);
    Message createMessage(String message,User user);
    User findUserById(long id);
}

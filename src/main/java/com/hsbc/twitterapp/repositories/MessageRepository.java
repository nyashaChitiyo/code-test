package com.hsbc.twitterapp.repositories;

import com.hsbc.twitterapp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}

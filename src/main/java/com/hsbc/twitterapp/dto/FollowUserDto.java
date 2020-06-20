package com.hsbc.twitterapp.dto;

import lombok.Data;

@Data
public class FollowUserDto {

    private long userId;
    private long followerId;
}

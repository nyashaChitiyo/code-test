package com.hsbc.twitterapp.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Followers {

    @Id
    //private long id;
    private long followerId;

    @ManyToOne
    @JoinColumn
    private User users;

    @Override
    public String toString() {
        return "Followers{" +
                "followerId=" + followerId +
        //        ", users=" + users +
                '}';
    }
}

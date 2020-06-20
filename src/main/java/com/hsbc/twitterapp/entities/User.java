package com.hsbc.twitterapp.entities;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    //@Column(unique=true)
    private long userId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Message> messageList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Followers> followers;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                //", messageList=" + messageList +
                ", followers=" + followers +
                '}';
    }
}

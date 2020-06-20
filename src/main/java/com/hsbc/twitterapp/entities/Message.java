package com.hsbc.twitterapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private long userNumber;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

    @ManyToOne
    @JoinColumn
    private User user;

    @JsonIgnore
    @JsonProperty(value = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(long userNumber) {
        this.userNumber = userNumber;
    }

    @JsonIgnore
    @JsonProperty(value = "creationDate")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @JsonBackReference(value = "user-values")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", userNumber=" + userNumber +
                ", creationDate=" + creationDate +
               // ", user=" + user +
                '}';
    }

}

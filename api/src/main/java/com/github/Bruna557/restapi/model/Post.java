package com.github.Bruna557.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private long postId;

    @Column(name = "post_date", nullable = false)
    private Timestamp postDate;

    @Column(name = "post_text", nullable = false)
    private String postText;

    @Column(name = "post_votes")
    private long postVotes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public long getPostVotes() {
        return postVotes;
    }

    public void setPostVotes(long postVotes) {
        this.postVotes = postVotes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

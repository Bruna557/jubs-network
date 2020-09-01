package com.github.Bruna557.restapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private long postId;

    @Column(name = "post_date", nullable = false)
    private Date postDate = new Date();

    @Column(name = "post_text", nullable = false)
    private String postText;

    @Column(name = "post_votes")
    private long postVotes;

    @Column(name = "user_id")
    private long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Post(String postText, long userId) {
        this.postText = postText;
        this.userId = userId;
    }
}
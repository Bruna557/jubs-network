package com.github.Bruna557.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "followings")
@IdClass(FollowId.class)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Follow {
    @Id
    @Column(name = "follower_id")
    private long followerId;

    @Id
    @Column(name = "followed_id")
    private long followedId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "follower_id", insertable = false, updatable = false)
    private User follower;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "followed_id", insertable = false, updatable = false)
    private User followed;

    public Follow(long followerId, long followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }
}

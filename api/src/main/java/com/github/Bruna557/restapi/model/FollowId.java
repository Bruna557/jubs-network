package com.github.Bruna557.restapi.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class FollowId implements Serializable {
    private Long followerId;
    private Long followedId;
}

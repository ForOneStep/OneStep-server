package com.onestep.server.entity.like;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class LikeAnswerClass implements Serializable {
    private Long answerId;
    private String userId;
}

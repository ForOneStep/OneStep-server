package com.onestep.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onestep.server.entity.like.LikeAnswerClass;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LikeAnswer {
    @EmbeddedId
    private LikeAnswerClass likeId;
    //외래키
    @ManyToOne
    @JoinColumn(name="answer_id")
    @MapsId("answerId")
    @JsonIgnore
    private Answer answer;
    //외래키
    @ManyToOne
    @JoinColumn(name="user_id")
    @MapsId("userId")
    @JsonIgnore
    private User user;
}

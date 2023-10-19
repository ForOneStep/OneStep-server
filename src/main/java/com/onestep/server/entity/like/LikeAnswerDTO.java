package com.onestep.server.entity.like;


import com.onestep.server.entity.Answer;
import com.onestep.server.entity.LikeAnswer;
import com.onestep.server.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeAnswerDTO {

    private LikeAnswerClass likeId;
    private Answer answer;
    private User user;

    public LikeAnswer toEntity(){
        return LikeAnswer.builder()
                .likeId(likeId)
                .answer(answer)
                .user(user)
                .build();
    }
}

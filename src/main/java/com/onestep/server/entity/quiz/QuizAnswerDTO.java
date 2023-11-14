package com.onestep.server.entity.quiz;

import com.onestep.server.entity.Quiz;
import com.onestep.server.entity.QuizAnswer;
import com.onestep.server.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizAnswerDTO {

    private Quiz quiz;
    private User user;
    private Integer quiz_ans;


    public QuizAnswer toEntity(){
        return QuizAnswer.builder()
                .quizAnswer_id(null)
                .quiz(quiz)
                .user(user)
                .quiz_ans(quiz_ans)
                .build();
    }
}

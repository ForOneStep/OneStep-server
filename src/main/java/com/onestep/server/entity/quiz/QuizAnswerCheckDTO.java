package com.onestep.server.entity.quiz;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class QuizAnswerCheckDTO {

    private Long quizAnswer_id;
    private String user_id;
    private Integer quiz_ans;
    private Integer quiz_state;
}

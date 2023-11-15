package com.onestep.server.entity.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class QuizRequestDTO {
    private String user_id;
    private String quiz_txt;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private Integer quiz_ans;
}

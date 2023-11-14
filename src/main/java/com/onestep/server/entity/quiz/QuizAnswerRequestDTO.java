package com.onestep.server.entity.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizAnswerRequestDTO {

    private Long quiz_id;
    private String user_id;
    private Integer quiz_ans;
}

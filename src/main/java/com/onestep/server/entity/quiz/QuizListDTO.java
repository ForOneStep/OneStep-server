package com.onestep.server.entity.quiz;

import com.onestep.server.entity.QuizAnswer;
import com.onestep.server.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class QuizListDTO {
    private Long quiz_id;
    private String writer_id;
    private String writer_nickname;
    private String quiz_txt;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private Integer quiz_ans;
    private Date write_date;
    private List<QuizAnswerCheckDTO> quizAnswers;
}

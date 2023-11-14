package com.onestep.server.entity.quiz;

import com.onestep.server.entity.Quiz;
import com.onestep.server.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class QuizDTO {

    private User user;
    private String quiz_txt;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private Integer quiz_ans;
    private Date write_date;

    public Quiz toEntity(){
        return Quiz.builder()
                .quiz_id(null)
                .user(user)
                .quiz_txt(quiz_txt)
                .answer1(answer1)
                .answer2(answer2)
                .answer3(answer3)
                .answer4(answer4)
                .quiz_ans(quiz_ans)
                .write_date(write_date).build();
    }
}

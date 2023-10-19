package com.onestep.server.entity.answer;


import com.onestep.server.entity.Answer;
import com.onestep.server.entity.LikeAnswer;
import com.onestep.server.entity.Question;
import com.onestep.server.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AnswerDTO {

    private String answer_txt;
    private User user;
    private Date answer_date;
    private Question question;

    public Answer toEntity(){
        return Answer.builder()
                .answer_id(null)
                .user(user)
                .answer_txt(answer_txt)
                .write_date(answer_date)
                .question(question)
                .build();
    }
}

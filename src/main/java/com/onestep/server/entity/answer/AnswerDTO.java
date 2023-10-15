package com.onestep.server.entity.answer;


import com.onestep.server.entity.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AnswerDTO {

    private String answer_txt;
    private String user_id;
    private Date answer_date;
    private Long question_id;
    public Answer toEntity(){
        return Answer.builder()
                .answer_id(null)
                .user_id(user_id)
                .answer_txt(answer_txt)
                .write_date(answer_date)
                .question_id(question_id)
                .answer_liked(0)
                .build();
    }
}

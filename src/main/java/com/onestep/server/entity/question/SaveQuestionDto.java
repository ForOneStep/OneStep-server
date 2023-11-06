package com.onestep.server.entity.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onestep.server.entity.Answer;
import com.onestep.server.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SaveQuestionDto {

    private Date question_date;

    private String question_txt;

    public Question toEntity(){
        return Question.builder()
                .question_date(question_date)
                .question_id(null)
                .question_txt(question_txt)
                .build();
    }
}

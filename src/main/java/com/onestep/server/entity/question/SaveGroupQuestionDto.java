package com.onestep.server.entity.question;

import com.onestep.server.entity.GroupQuestion;
import com.onestep.server.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SaveGroupQuestionDto {
    private Date question_date;

    private int group_number;

    private String question_txt;

    public GroupQuestion toEntity(){
        return GroupQuestion.builder()
                .question_date(question_date)
                .question_id(null)
                .question_txt(question_txt)
                .group_number(group_number)
                .build();
    }
}

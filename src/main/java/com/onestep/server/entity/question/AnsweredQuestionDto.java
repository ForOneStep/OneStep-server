package com.onestep.server.entity.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AnsweredQuestionDto implements Comparable<AnsweredQuestionDto> {
    private Long question_id;
    private Date question_date;
    private String question_txt;
    private Boolean is_group_question;

    @Override
    public int compareTo(AnsweredQuestionDto a) {
        return (int) (question_id-a.question_id);
    }
}

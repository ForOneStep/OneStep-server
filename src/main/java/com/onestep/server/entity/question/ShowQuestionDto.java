package com.onestep.server.entity.question;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShowQuestionDto {

    private Long question_id;

    private Date date;

    private int group_number;

    private String question_txt;
}

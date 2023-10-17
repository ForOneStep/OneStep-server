package com.onestep.server.entity.answer;

import com.onestep.server.entity.Family;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AnswerReturnDTO {
    private Long answer_id;
    private Long question_id;
    private String user_id;
    private String user_nickname;
    private String profile_path;
    private String answer_txt;
    private String answer_img;
    private Date write_date;
    private int answer_liked;


}

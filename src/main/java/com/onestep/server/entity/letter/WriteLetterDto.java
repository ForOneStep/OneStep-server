package com.onestep.server.entity.letter;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WriteLetterDto {
    private String writer_id;
    private String letter_title;
    private String letter_txt;
}

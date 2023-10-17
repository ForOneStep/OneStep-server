package com.onestep.server.entity.letter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class LetterListDTO {

    private Long letter_id;
    private String writer_id;
    private String family_id;
    private String letter_title;
    private String letter_txt;
    private Date write_date;

}

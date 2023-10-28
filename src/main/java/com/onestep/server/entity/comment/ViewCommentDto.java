package com.onestep.server.entity.comment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ViewCommentDto {
    private String writer_id;
    private Long root_comment_id;
    private String comment_txt;
    private Date write_date;
}

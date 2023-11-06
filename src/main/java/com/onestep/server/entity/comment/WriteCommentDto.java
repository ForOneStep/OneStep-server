package com.onestep.server.entity.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WriteCommentDto {
    private Long answer_id;
    private Long root_comment_id;
    private String writer_id;
    private String comment_txt;
}

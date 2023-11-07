package com.onestep.server.entity.photoBookComment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WritePhotoBookCommentDTO {
    private Long photoBook_id;
    private Long root_comment_id;
    private String writer_id;
    private String comment_txt;
}

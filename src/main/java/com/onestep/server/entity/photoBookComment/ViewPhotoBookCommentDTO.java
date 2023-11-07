package com.onestep.server.entity.photoBookComment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ViewPhotoBookCommentDTO {
    private String user_nickname;
    private String profile_path;
    private Long root_comment_id;
    private String comment_txt;
    private Date write_date;
}

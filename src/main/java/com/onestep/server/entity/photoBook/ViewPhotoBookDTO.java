package com.onestep.server.entity.photoBook;

import com.onestep.server.entity.PhotoBookComment;
import com.onestep.server.entity.photoBookComment.ViewPhotoBookCommentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ViewPhotoBookDTO {
    private Long photo_id;
    private String user_nickname;
    private String profile_path;
    private String photo_img;
    private String photo_txt;
    private Date write_date;
    private List<ViewPhotoBookCommentDTO> ViewPhotoBookCommentDTO = new ArrayList<>();
}

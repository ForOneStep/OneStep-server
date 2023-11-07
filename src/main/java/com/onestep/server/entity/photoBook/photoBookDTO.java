package com.onestep.server.entity.photoBook;

import com.onestep.server.entity.PhotoBook;
import com.onestep.server.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class photoBookDTO {
    private User user;
    private String photo_img;
    private String photo_txt;
    private Date write_date;


    public PhotoBook toEntity(){
        return PhotoBook.builder()
                .photo_id(null)
                .user(user)
                .photo_img(photo_img)
                .photo_txt(photo_txt)
                .write_date(write_date)
                .build();
    }
}

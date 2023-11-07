package com.onestep.server.controller.photoBook;

import com.onestep.server.entity.PhotoBook;
import com.onestep.server.entity.PhotoBookComment;
import com.onestep.server.entity.photoBook.ViewPhotoBookDTO;
import com.onestep.server.entity.photoBookComment.ViewPhotoBookCommentDTO;
import com.onestep.server.service.image.S3Uploader;
import com.onestep.server.service.photoBook.PhotoBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PhotoBookController {

    private final PhotoBookService photoBookService;
    private final S3Uploader s3Uploader;

    // 사진첩 사진 등록
    @PostMapping(value = "/photobook/write/{user_id}", consumes = {MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
    public String writePhotoBook(@PathVariable String user_id, @RequestPart(value = "writeTxt") String writeTxt, @RequestPart(value = "img") MultipartFile img) throws IOException {
        PhotoBook photoBook = null;
        String url = s3Uploader.upload(img, "photoBook");
        photoBook = photoBookService.writePhotoBook(user_id, writeTxt, url);
        return photoBook.getUser().getUser_nickname()+"님이 등록한 사진이 사진첩에 업로드 되었습니다.";
    }

    // 사진첩 사진 리스트 확인
    @GetMapping(value = "/photobook/read/{family_id}")
    public List<ViewPhotoBookDTO> readPhotoBook(@PathVariable String family_id){
        List<PhotoBook> photoBooks = photoBookService.readPhotoBook(family_id);
        List<ViewPhotoBookDTO> viewPhotoBookDTOS = new ArrayList<>();
        List<ViewPhotoBookCommentDTO> viewPhotoBookCommentDTOS = new ArrayList<>();

        for(PhotoBook p : photoBooks){
            ViewPhotoBookDTO viewPhotoBookDTO = new ViewPhotoBookDTO();
            viewPhotoBookDTO.setPhoto_id(p.getPhoto_id());
            viewPhotoBookDTO.setUser_nickname(p.getUser().getUser_nickname());
            viewPhotoBookDTO.setProfile_path(p.getUser().getProfile_path());
            viewPhotoBookDTO.setPhoto_img(p.getPhoto_img());
            viewPhotoBookDTO.setPhoto_txt(p.getPhoto_txt());
            viewPhotoBookDTO.setWrite_date(p.getWrite_date());
            for(PhotoBookComment c : p.getPhotoBookComments()) {
                ViewPhotoBookCommentDTO viewPhotoBookCommentDTO = new ViewPhotoBookCommentDTO();
                viewPhotoBookCommentDTO.setUser_nickname(c.getUser().getUser_nickname());
                viewPhotoBookCommentDTO.setProfile_path(c.getUser().getProfile_path());
                if(c.getPhotoBookComment() != null){
                    viewPhotoBookCommentDTO.setRoot_comment_id(c.getPhotoBookComment().getPhotoBookComment_id());
                }
                viewPhotoBookCommentDTO.setComment_txt(c.getComment_txt());
                viewPhotoBookCommentDTO.setWrite_date(c.getWrite_date());

                viewPhotoBookCommentDTOS.add(viewPhotoBookCommentDTO);
            }
            viewPhotoBookDTO.setViewPhotoBookCommentDTO(viewPhotoBookCommentDTOS);
            viewPhotoBookDTOS.add(viewPhotoBookDTO);
        }
        return viewPhotoBookDTOS;
    }
    // 사진첩 사진 삭제

    // 사진첩 사진 수정
}

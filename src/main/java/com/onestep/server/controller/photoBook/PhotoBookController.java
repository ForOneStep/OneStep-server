package com.onestep.server.controller.photoBook;

import com.amazonaws.services.cloudformation.model.StackInstance;
import com.onestep.server.entity.PhotoBook;
import com.onestep.server.entity.PhotoBookComment;
import com.onestep.server.entity.photoBook.ViewPhotoBookDTO;
import com.onestep.server.entity.photoBookComment.ViewPhotoBookCommentDTO;
import com.onestep.server.repository.IPhotoBookRepository;
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

    private final IPhotoBookRepository iPhotoBookRepository;
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
        List<ViewPhotoBookDTO> viewPhotoBookDTOS = photoBookService.readPhotoBook(family_id);

        return viewPhotoBookDTOS;
    }
    // 사진첩 사진 삭제
    @DeleteMapping(value = "/photobook/delete/{photoBook_id}")
    public String deletePhotoBook(@PathVariable Long photoBook_id){

        photoBookService.deletePhotoBook(photoBook_id);
        return photoBook_id+"번 사진첩 삭제 완료";
    }
    // 사진첩 사진 수정
}

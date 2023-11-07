package com.onestep.server.controller.photoBookComment;

import com.onestep.server.entity.photoBookComment.WritePhotoBookCommentDTO;
import com.onestep.server.service.photoBookComment.PhotoBookCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhotoBookCommentController {
    private final PhotoBookCommentService photoBookCommentService;

    @PostMapping("/photoBookcomment/writeComment")
    public String writeComment(@RequestBody WritePhotoBookCommentDTO writePhotoBookCommentDTO){
        return photoBookCommentService.writeComment(writePhotoBookCommentDTO);
    }


}

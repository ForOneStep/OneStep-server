package com.onestep.server.controller.comment;

import com.onestep.server.entity.comment.WriteCommentDto;
import com.onestep.server.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/writeComment")
    public String writeComment(@RequestBody WriteCommentDto writeCommentDto){
        return commentService.writeComment(writeCommentDto);
    }
}

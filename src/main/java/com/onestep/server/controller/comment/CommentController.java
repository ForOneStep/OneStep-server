package com.onestep.server.controller.comment;

import com.onestep.server.entity.comment.WriteCommentDto;
import com.onestep.server.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/writeComment")
    public String writeComment(WriteCommentDto writeCommentDto){
        System.out.println(writeCommentDto.getRoot_comment_id());
        System.out.println(writeCommentDto.getComment_txt());
        System.out.println(writeCommentDto.getWriter_id());

        return commentService.writeComment(writeCommentDto);
    }
}

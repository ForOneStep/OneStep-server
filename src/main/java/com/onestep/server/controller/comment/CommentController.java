package com.onestep.server.controller.comment;

import com.onestep.server.entity.comment.ViewCommentDto;
import com.onestep.server.entity.comment.WriteCommentDto;
import com.onestep.server.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/writeComment")
    public String writeComment(@RequestBody WriteCommentDto writeCommentDto){
        return commentService.writeComment(writeCommentDto);
    }

    @GetMapping("/comment/viewComment/{answerId}")
    public List<ViewCommentDto> viewComment(@PathVariable Long answerId){
        return commentService.viewComment(answerId);
    }
}

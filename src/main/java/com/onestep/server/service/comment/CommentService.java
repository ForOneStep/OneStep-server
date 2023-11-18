package com.onestep.server.service.comment;

import com.onestep.server.entity.Comment;
import com.onestep.server.entity.comment.ViewCommentDto;
import com.onestep.server.entity.comment.WriteCommentDto;
import com.onestep.server.repository.IAnswerRepository;
import com.onestep.server.repository.ICommentRepository;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final IUserRepository iUserRepository;
    private final ICommentRepository iCommentRepository;
    private final IAnswerRepository iAnswerRepository;

    public String writeComment(WriteCommentDto writeCommentDto){
        Comment comment = new Comment();

        if(iAnswerRepository.findById(writeCommentDto.getAnswer_id()).isPresent()){
            comment.setAnswer(iAnswerRepository.findById(writeCommentDto.getAnswer_id()).get());
        }else{
            //todo 오류처리
        }

        if(iUserRepository.findById(writeCommentDto.getWriter_id()).isPresent()){
            comment.setUser(iUserRepository.findById(writeCommentDto.getWriter_id()).get());
        }else{
            //todo 오류처리
        }

        if(writeCommentDto.getRoot_comment_id()!=null){
            if(iCommentRepository.findById(writeCommentDto.getRoot_comment_id()).isPresent()){
                comment.setComment(iCommentRepository.findById(writeCommentDto.getRoot_comment_id()).get());
            }else{
                //todo 오류처리
            }
        }else{
            comment.setComment(null);
        }

        comment.setComment_txt(writeCommentDto.getComment_txt());

        Date now = new Date();
        comment.setWrite_date(now);

        iCommentRepository.save(comment);
        return "["+comment.getComment_txt()+"] 댓글이 작성되었습니다.";
    }

    public List<ViewCommentDto> viewComment(Long answerId){
        List<Comment> commentList = iCommentRepository.findByAnswerId(answerId);
        List<ViewCommentDto> viewCommentDtoList = new ArrayList<>();

        commentList.forEach(comment -> {
            ViewCommentDto viewCommentDto = new ViewCommentDto();

            viewCommentDto.setComment_txt(comment.getComment_txt());
            viewCommentDto.setComment_id(comment.getComment_id());
            if(comment.getComment()!=null){
                viewCommentDto.setRoot_comment_id(comment.getComment().getComment_id());
            }
            viewCommentDto.setWriter_nickname(comment.getUser().getUser_nickname());
            viewCommentDto.setWriter_profile(comment.getUser().getProfile_path());
            viewCommentDto.setWrite_date(comment.getWrite_date());

            viewCommentDtoList.add(viewCommentDto);
        });

        return viewCommentDtoList;
    }
}

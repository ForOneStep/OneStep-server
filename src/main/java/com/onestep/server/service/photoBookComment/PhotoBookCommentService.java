package com.onestep.server.service.photoBookComment;

import com.onestep.server.entity.PhotoBookComment;
import com.onestep.server.entity.photoBookComment.WritePhotoBookCommentDTO;
import com.onestep.server.repository.IPhotoBookCommentRepository;
import com.onestep.server.repository.IPhotoBookRepository;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class PhotoBookCommentService {
    private final IUserRepository iUserRepository;
    private final IPhotoBookRepository iPhotoBookRepository;
    private final IPhotoBookCommentRepository iPhotoBookCommentRepository;

    public String writeComment(WritePhotoBookCommentDTO writePhotoBookCommentDTO){
        PhotoBookComment photoBookComment = new PhotoBookComment();

        if(iPhotoBookRepository.findById(writePhotoBookCommentDTO.getPhotoBook_id()).isPresent()){
            photoBookComment.setPhotoBook(iPhotoBookRepository.findById(writePhotoBookCommentDTO.getPhotoBook_id()).get());
        }else{
            //todo 오류처리
        }

        if(iUserRepository.findById(writePhotoBookCommentDTO.getWriter_id()).isPresent()){
            photoBookComment.setUser(iUserRepository.findById(writePhotoBookCommentDTO.getWriter_id()).get());
        }else{
            //todo 오류처리
        }

        if(writePhotoBookCommentDTO.getRoot_comment_id()!=null){
            if(iPhotoBookCommentRepository.findById(writePhotoBookCommentDTO.getRoot_comment_id()).isPresent()){
                photoBookComment.setPhotoBookComment(iPhotoBookCommentRepository.findById(writePhotoBookCommentDTO.getRoot_comment_id()).get());
            }else{
                //todo 오류처리
            }
        }else{
            photoBookComment.setPhotoBookComment(null);
        }

        photoBookComment.setComment_txt(writePhotoBookCommentDTO.getComment_txt());

        Date now = new Date();
        photoBookComment.setWrite_date(now);

        iPhotoBookCommentRepository.save(photoBookComment);
        return "["+photoBookComment.getComment_txt()+"] 댓글이 작성되었습니다.";
    }


}


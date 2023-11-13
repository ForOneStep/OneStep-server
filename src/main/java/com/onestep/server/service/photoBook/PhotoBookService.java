package com.onestep.server.service.photoBook;


import com.onestep.server.entity.Family;
import com.onestep.server.entity.PhotoBook;
import com.onestep.server.entity.PhotoBookComment;
import com.onestep.server.entity.User;
import com.onestep.server.entity.photoBook.PhotoBookDTO;
import com.onestep.server.entity.photoBook.ViewPhotoBookDTO;
import com.onestep.server.entity.photoBookComment.ViewPhotoBookCommentDTO;
import com.onestep.server.repository.IFamilyRepository;
import com.onestep.server.repository.IPhotoBookRepository;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PhotoBookService {
    private final IPhotoBookRepository iPhotoBookRepository;
    private final IUserRepository iUserRepository;
    private final IFamilyRepository iFamilyRepository;

    //사진첩에 사진 등록
    public PhotoBook writePhotoBook(String user_id, String writeTxt,String url){
        Optional<User> optionalUser = iUserRepository.findById(user_id);
        User user = optionalUser.get();

        Date date = new Date();
        PhotoBookDTO photoBookDTO = new PhotoBookDTO();
        photoBookDTO.setUser(user);
        photoBookDTO.setPhoto_txt(writeTxt);
        photoBookDTO.setWrite_date(date);
        photoBookDTO.setPhoto_img(url);

        PhotoBook addPhotoBook = iPhotoBookRepository.save(photoBookDTO.toEntity());

        return addPhotoBook;
    }

    //사진첩 리스트 확인
    public List<ViewPhotoBookDTO> readPhotoBook(String family_id){
        Optional<Family> optionalFamily = iFamilyRepository.findById(family_id);
        Family family = optionalFamily.get();
        List<PhotoBook> photoBooks = iPhotoBookRepository.findPhotoBookByFamilyId(family);
        List<ViewPhotoBookDTO> viewPhotoBookDTOS = new ArrayList<>();

        for(PhotoBook p : photoBooks){
            List<ViewPhotoBookCommentDTO> viewPhotoBookCommentDTOS = new ArrayList<>();
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
}

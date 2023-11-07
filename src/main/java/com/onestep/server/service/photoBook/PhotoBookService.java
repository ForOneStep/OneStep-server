package com.onestep.server.service.photoBook;


import com.onestep.server.entity.PhotoBook;
import com.onestep.server.entity.User;
import com.onestep.server.entity.photoBook.photoBookDTO;
import com.onestep.server.repository.IFamilyRepository;
import com.onestep.server.repository.IPhotoBookRepository;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        photoBookDTO photoBookDTO = new photoBookDTO();
        photoBookDTO.setUser(user);
        photoBookDTO.setPhoto_txt(writeTxt);
        photoBookDTO.setWrite_date(date);
        photoBookDTO.setPhoto_img(url);

        PhotoBook addPhotoBook = iPhotoBookRepository.save(photoBookDTO.toEntity());

        return addPhotoBook;
    }
}

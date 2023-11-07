package com.onestep.server.service.photoBook;


import com.onestep.server.entity.Family;
import com.onestep.server.entity.PhotoBook;
import com.onestep.server.entity.User;
import com.onestep.server.entity.photoBook.PhotoBookDTO;
import com.onestep.server.repository.IFamilyRepository;
import com.onestep.server.repository.IPhotoBookRepository;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<PhotoBook> readPhotoBook(String family_id){
        Optional<Family> optionalFamily = iFamilyRepository.findById(family_id);
        Family family = optionalFamily.get();
        List<PhotoBook> photoBooks = iPhotoBookRepository.findPhotoBookByFamilyId(family);
        return photoBooks;
    }
}

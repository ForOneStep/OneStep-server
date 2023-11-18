package com.onestep.server.repository;

import com.onestep.server.entity.Family;
import com.onestep.server.entity.PhotoBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPhotoBookRepository extends JpaRepository<PhotoBook, Long> {
    // 가족 아이디로 사진첩 목록 확인
    @Query("SELECT p FROM PhotoBook p WHERE p.user.family =:family ")
    List<PhotoBook> findPhotoBookByFamilyId(@Param("family") Family family);

    @Modifying
    @Transactional
    @Query("delete from PhotoBook p where p.photo_id =:photoBook_id")
    void deleteByPhotoBookId(@Param("photoBook_id") Long photoBook_id);

}

package com.onestep.server.repository;

import com.onestep.server.entity.Family;
import com.onestep.server.entity.PhotoBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPhotoBookRepository extends JpaRepository<PhotoBook, Long> {
    // 가족 아이디로 사진첩 목록 확인
    @Query("SELECT p, u FROM PhotoBook p LEFT JOIN User u ON p.user = u WHERE u.family =:family ")
    List<PhotoBook> findPhotoBookByFamilyId(@Param("family") Family family);

}

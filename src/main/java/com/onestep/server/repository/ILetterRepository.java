package com.onestep.server.repository;

import com.onestep.server.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ILetterRepository extends JpaRepository<Letter, Long> {
//    //특정 사람이 작성한 익명 편지 리스트 확인
//    @Query("SELECT l FROM Letter l WHERE l.user_id =:id")
//    List<Letter> findLetterByWriterId(@Param("id") String id);
//
//    //가족 아이디로 편지 목록 확인
//    @Query("SELECT l, u FROM Letter l LEFT JOIN User u ON l.user_id = u.user_id WHERE u.fam_id =:fam_id")
//    List<Letter> findLetterByFamilyId(@Param("fam_id")String fam_id);
}

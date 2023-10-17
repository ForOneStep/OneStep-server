package com.onestep.server.repository;

import com.onestep.server.entity.Family;
import com.onestep.server.entity.Letter;
import com.onestep.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ILetterRepository extends JpaRepository<Letter, Long> {
    //특정 사람이 작성한 익명 편지 리스트 확인
    @Query("SELECT l FROM Letter l WHERE l.user =:user")
    List<Letter> findLetterByWriterId(@Param("user") User user);

    //가족 아이디로 편지 목록 확인
    @Query("SELECT l, u FROM Letter l LEFT JOIN User u ON l.user = u WHERE u.family =:family")
    List<Letter> findLetterByFamilyId(@Param("family") Family family);
}

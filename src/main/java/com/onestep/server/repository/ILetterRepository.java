package com.onestep.server.repository;

import com.onestep.server.entity.Family;
import com.onestep.server.entity.Letter;
import com.onestep.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ILetterRepository extends JpaRepository<Letter, Long> {
    //특정 사람이 작성한 익명 쪽지 리스트 확인
    @Query("SELECT l FROM Letter l WHERE l.user =:user")
    List<Letter> findLetterByWriterId(@Param("user") User user);

    //가족 아이디로 과거 쪽지 목록 확인
    @Query("SELECT l, u FROM Letter l LEFT JOIN User u ON l.user = u WHERE u.family =:family and l.letter_state>0")
    List<Letter> findLetterByFamilyId(@Param("family") Family family);

    //가족 아이디로 이번주 공개된 쪽지 목록 확인
    @Query("SELECT l, u FROM Letter l LEFT JOIN User u ON l.user = u WHERE u.family =:family and l.letter_state=1")
    List<Letter> findWeeklyLetterByFamilyId(@Param("family") Family family);

    //쪽지 상태 변경
    @Query("UPDATE Letter l SET l.letter_state = l.letter_state+1  WHERE l.letter_state<2")
    void changeLetterState();
}

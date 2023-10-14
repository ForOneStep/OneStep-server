package com.onestep.server.repository;

import com.onestep.server.entity.Answer;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAnswerRepository extends JpaRepository<Answer,Long> {

    // 답변 읽기
    @Query("select a from Answer a left join User u on a.user_id = u.user_id where a.question_id = :questionId and u.fam_id =:familyId")
    List<Answer> findAnswerByFamId(@Param("questionId") Long questionId, @Param("familyId") String familyId);
//   "select a from Answer a User u where a.question_id = :questionId and a.user_id in (select u.user_id from u where u.fam_id = :family_id)
}

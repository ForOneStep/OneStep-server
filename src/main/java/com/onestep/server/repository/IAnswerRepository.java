package com.onestep.server.repository;

import com.onestep.server.entity.Answer;
import com.onestep.server.entity.Family;
import com.onestep.server.entity.Question;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAnswerRepository extends JpaRepository<Answer,Long> {

    // 답변 읽기
    @Query("select a from Answer a left join User u on a.user.user_id = u.user_id where a.question = :question and u.family =:family")
    List<Answer> findAnswerByFamId(@Param("question")Question question, @Param("family")Family family);
}

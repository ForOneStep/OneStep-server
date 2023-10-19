package com.onestep.server.repository;

import com.onestep.server.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IQuestionRepository extends JpaRepository<Question, Long> {

    //질문 목록 확인
    @Query("SELECT q FROM Question q WHERE q.question_id <=:questionId")
    List<Question> findListById(@Param("questionId") Long questionId);
}

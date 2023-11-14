package com.onestep.server.repository;

import com.onestep.server.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface IQuestionRepository extends JpaRepository<Question, Long> {

    //단일 질문 확인
    @Query("SELECT q FROM Question q WHERE q.question_date =DATE(:date)")
    Optional<Question> findQuestionByDate(@Param("date") Date date);
}

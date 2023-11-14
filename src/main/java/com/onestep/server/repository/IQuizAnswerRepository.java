package com.onestep.server.repository;

import com.onestep.server.entity.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface IQuizAnswerRepository extends JpaRepository<QuizAnswer, Long> {
    @Query("SELECT q FROM QuizAnswer q WHERE q.quiz.quiz_id =:quiz_id and q.user.user_id =:user_id")
    Optional<QuizAnswer> findQuizByUser(@Param("quiz_id") Long quiz_id, @Param("user_id") String user_id);
}

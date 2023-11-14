package com.onestep.server.repository;

import com.onestep.server.entity.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuizAnswerRepository extends JpaRepository<QuizAnswer, Long> {
}

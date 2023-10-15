package com.onestep.server.repository;

import com.onestep.server.entity.Letter;
import com.onestep.server.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepository extends JpaRepository<Question, Long> {
}

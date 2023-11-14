package com.onestep.server.repository;

import com.onestep.server.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuizRepository extends JpaRepository<Quiz, Long> {

}

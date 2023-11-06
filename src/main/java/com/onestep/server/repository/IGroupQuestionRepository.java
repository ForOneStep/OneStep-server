package com.onestep.server.repository;

import com.onestep.server.entity.GroupQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupQuestionRepository extends JpaRepository<GroupQuestion, Long> {
}

package com.onestep.server.repository;

import com.onestep.server.entity.GroupQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface IGroupQuestionRepository extends JpaRepository<GroupQuestion, Long> {
    //단일 질문 확인
    @Query("SELECT q FROM GroupQuestion q WHERE q.question_date =DATE(:date) and q.group_number =:groupNumber")
    Optional<GroupQuestion> findGroupQuestionByDate(@Param("date") Date date, @Param("groupNumber") int groupNumber);
}

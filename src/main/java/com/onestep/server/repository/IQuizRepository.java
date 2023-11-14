package com.onestep.server.repository;

import com.onestep.server.entity.Family;
import com.onestep.server.entity.Letter;
import com.onestep.server.entity.Quiz;
import com.onestep.server.entity.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IQuizRepository extends JpaRepository<Quiz, Long> {

    // 날짜로 퀴즈 등록 여부 찾기
    @Query("SELECT q FROM Quiz q WHERE q.user.family.fam_id =:family_id and q.write_date =:date")
    Optional<Quiz> findQuizByWriteDate(@Param("family_id") String family_id,@Param("date") Date date);

}

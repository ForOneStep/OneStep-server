package com.onestep.server.repository;

import com.onestep.server.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where answer_id =:answerId")
    List<Comment> findByAnswerId(Long answerId);
}

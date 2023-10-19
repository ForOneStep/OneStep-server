package com.onestep.server.repository;

import com.onestep.server.entity.LikeAnswer;
import com.onestep.server.entity.like.LikeAnswerClass;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ILikeAnswerRepository extends JpaRepository<LikeAnswer, LikeAnswerClass> {


}

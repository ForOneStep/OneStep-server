package com.onestep.server.repository;

import com.onestep.server.entity.Family;
import com.onestep.server.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, String> {

    //가족 아이디로 가족 구성원 확인
    @Query("select u from User u where u.family =:family")
    List<User> findUserByFamily(@Param("family")Family family);
}

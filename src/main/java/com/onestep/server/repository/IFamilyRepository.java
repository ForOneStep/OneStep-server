package com.onestep.server.repository;

import com.onestep.server.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IFamilyRepository extends JpaRepository<Family,String> {

    @Query("UPDATE Family f SET f.question_id = f.question_id+1 , f.is_valid = false  WHERE f.is_valid=true")
    void changeQuestionAndState();

}

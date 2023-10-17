package com.onestep.server.repository;

import com.onestep.server.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFamilyRepository extends JpaRepository<Family,String> {
}

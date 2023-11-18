package com.onestep.server.repository;

import com.onestep.server.entity.PhotoBookComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPhotoBookCommentRepository extends JpaRepository<PhotoBookComment,Long> {
    @Query("select c from PhotoBookComment c where c.photoBook.photo_id =:photoBookId")
    List<PhotoBookComment> findByPhotoBookId(@Param("photoBookId")Long photoBookId);

}

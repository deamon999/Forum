package com.gmail.deamon999.reposotories;


import com.gmail.deamon999.entities.Category;
import com.gmail.deamon999.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT c FROM Subject c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:pattern,'%'))")
    List<Subject> findByPattern(@Param("pattern") String pattern);

    @Query("SELECT c FROM Subject c WHERE c.category = :category")
    List<Subject> findByCategory(@Param("category") Category category);
}

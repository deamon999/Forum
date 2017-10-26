package com.gmail.deamon999.reposotories;




import com.gmail.deamon999.entities.Message;
import com.gmail.deamon999.entities.Subject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT c FROM Message c WHERE c.subject = :subject")
    List<Message> findBySubject(@Param("subject") Subject subject, Pageable pageable);
}

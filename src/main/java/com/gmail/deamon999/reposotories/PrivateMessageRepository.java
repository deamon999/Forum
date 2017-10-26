package com.gmail.deamon999.reposotories;



import com.gmail.deamon999.entities.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {
    @Query("SELECT c FROM PrivateMessage c WHERE c.recipientName = :recipientName")
    List<PrivateMessage> findByRecipient(@Param("recipientName") String recipientName);
}

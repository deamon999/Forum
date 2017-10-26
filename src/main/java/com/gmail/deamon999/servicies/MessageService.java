package com.gmail.deamon999.servicies;


import com.gmail.deamon999.entities.Message;
import com.gmail.deamon999.entities.Subject;
import com.gmail.deamon999.reposotories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public long messageCount() {
        return messageRepository.count();
    }

    @Transactional
    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    @Transactional
    public void deleteMessage(long id[]) {
        for (long i : id) {
            messageRepository.delete(i);
        }
    }

    @Transactional
    public List<Message> findAll(Pageable pageable) {
        return messageRepository.findAll(pageable).getContent();
    }

    @Transactional
    public List<Message> findBysubject(Subject subject, Pageable pageable) {
        return messageRepository.findBySubject(subject, pageable);
    }

    @Transactional(readOnly = true)
    public long count() {
        return messageRepository.count();
    }
}

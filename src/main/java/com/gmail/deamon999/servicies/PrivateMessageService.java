package com.gmail.deamon999.servicies;


import com.gmail.deamon999.entities.PrivateMessage;
import com.gmail.deamon999.reposotories.PrivateMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PrivateMessageService {
    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @Transactional
    public void addMessage(PrivateMessage message) {
        privateMessageRepository.save(message);
    }

    @Transactional
    public void deleteMessage(long id[]) {
        for (long i : id) {
            privateMessageRepository.delete(i);
        }
    }

    @Transactional
    public List<PrivateMessage> findByRecipient(String recipient) {
        return privateMessageRepository.findByRecipient(recipient);
    }
}

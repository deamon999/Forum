package com.gmail.deamon999.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "privatemessages")
@Data
@Getter
@Setter
@NoArgsConstructor
public class PrivateMessage {
    @Id
    @GeneratedValue
    private long id;
    private String recipientName;
    private String senderName;
    private String subject;
    private String text;
    private String date;

    public PrivateMessage(String recipientName, String senderName, String subject, String text, String date) {
        this.recipientName = recipientName;
        this.senderName = senderName;
        this.subject = subject;
        this.text = text;
        this.date = date;
    }
}

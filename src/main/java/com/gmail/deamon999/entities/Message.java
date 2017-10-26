package com.gmail.deamon999.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    private long id;
    private String text;
    private String senderName;
    private String date;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Message(String text, String senderName, String date, Subject subject) {
        this.text = text;
        this.senderName = senderName;
        this.date = date;
        this.subject = subject;
    }
}

package com.example.springboot_social_network.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "tag")
    private String tag;

    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }
}
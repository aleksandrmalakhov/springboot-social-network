package com.example.springboot_social_network.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message to long (more than 2048 symbols)")
    @Column(name = "text")
    private String text;

    @Length(max = 255, message = "Tag to long (more than 255 symbols)")
    @Column(name = "tag")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "filename")
    private String filename;

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }
}
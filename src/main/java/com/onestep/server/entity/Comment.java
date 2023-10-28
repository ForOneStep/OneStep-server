package com.onestep.server.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.Id;

import java.util.Date;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comment_id;

    //외래키
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="answer_id")
    private Answer answer;

    //외래키
    @ManyToOne
    @JoinColumn(name="root_comment_id")
    private Comment comment;

    private String comment_txt;

    //외래키
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;
}

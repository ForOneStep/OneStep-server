package com.onestep.server.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;

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
    private Long comment_id;

    //외래키
    @JoinColumn(name="answer_id")
    private Long answer_id;

    //외래키
    @JoinColumn(name="answer_id")
    private Long root_comment_id;

    //외래키
    @JoinColumn(name="user_id")
    private String user_id;

    @Column(length = 400, nullable = false)
    private String user_role;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;
}

package com.onestep.server.entity;

import javax.persistence.*;
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
    @ManyToOne
    @JoinColumn(name="answer_id")
    private Answer answer;

//    //외래키
//    @ManyToOne
//    @JoinColumn(name="comment_id", insertable = false, updatable = false)
//    private Comment comment;

    //외래키
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(length = 400, nullable = false)
    private String user_role;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;
}

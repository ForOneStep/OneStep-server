package com.onestep.server.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.Id;

import java.util.Date;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long answer_id;

    //외래키
    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    //외래키
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(length = 400, nullable = false)
    private String answer_txt;

    @Column(length = 400)
    private String answer_img;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;

    @ColumnDefault("0")
    private int answer_liked;
}

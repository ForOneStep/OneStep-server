package com.onestep.server.entity;

import javax.persistence.*;
import lombok.*;

import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @JoinColumn(name="group_question_id")
    private GroupQuestion groupQuestion;

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

    @OneToMany(mappedBy = "answer", cascade = {CascadeType.REMOVE})
    private List<LikeAnswer> like = new ArrayList<>();
}

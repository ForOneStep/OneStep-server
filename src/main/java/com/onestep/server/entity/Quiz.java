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
public class Quiz {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quiz_id;

    //외래키
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private String quiz_txt;

    private Integer quiz_ans;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;

    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    @OneToMany(mappedBy = "quiz", cascade = {CascadeType.REMOVE})
    private List<QuizAnswer> quizAnswers = new ArrayList<>();
}

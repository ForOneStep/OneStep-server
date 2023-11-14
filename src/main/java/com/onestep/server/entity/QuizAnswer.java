package com.onestep.server.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
public class QuizAnswer {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quizAnswer_id;

    @ManyToOne
    @JoinColumn(name="quiz_id")
    private Quiz quiz;

    //외래키
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private Integer quiz_ans;

    @ColumnDefault("2") //0 오답, 1 정답
    private Integer quiz_state;


}

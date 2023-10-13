package com.onestep.server.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import jakarta.persistence.Id;

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
    @Column(nullable = false)
    private Long answer_id;

    //외래키
    @JoinColumn(name="question_id")
    private Long question_id;

    //외래키
    @JoinColumn(name="user_id")
    private String user_id;

    @Column(length = 400, nullable = false)
    private String answer_txt;

    private String answer_img;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;

    @ColumnDefault("0")
    private int answer_liked;
}

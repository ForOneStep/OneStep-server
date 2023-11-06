package com.onestep.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Question {
    @Id
    @Column(nullable = false)
    private Long question_id;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    @Column(nullable = false)
    private Date question_date;

    @Column(nullable = false)
    private String question_txt;

}

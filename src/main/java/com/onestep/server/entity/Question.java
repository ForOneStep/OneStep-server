package com.onestep.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Id;

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

    @Column(nullable = false)
    private String question_txt;

    @ColumnDefault("0")
    private boolean has_image;

}

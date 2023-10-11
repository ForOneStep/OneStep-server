package com.onestep.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.*;
import org.springframework.data.annotation.Id;

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

}

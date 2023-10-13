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
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long letter_id;

    //외래키
    @JoinColumn(name="user_id")
    private String user_id;

    @Column(length = 100, nullable = false)
    private String letter_title;

    @Column(length = 1000)
    private String letter_txt;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;

}

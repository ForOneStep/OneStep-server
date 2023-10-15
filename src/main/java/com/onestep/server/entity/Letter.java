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
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long letter_id;

    //외래키
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(length = 100, nullable = false)
    private String letter_title;

    @Column(length = 1000)
    private String letter_txt;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;

}

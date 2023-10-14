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
public class Quiz {
    @Id
    @Column(nullable = false)
    private Long quiz_id;

    //외래키
    @JoinColumn(name="user_id")
    private String user_id;

    @Column(nullable = false)
    private String quiz_txt;

    @Column(length = 100, nullable = false)
    private String quiz_ans;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;
}

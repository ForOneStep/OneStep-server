package com.onestep.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class Family {
    @Id
    @Column(length = 100, nullable = false)
    private String fam_id;

    @Column(length = 100, nullable = false)
    private String fam_nickname;

    @ColumnDefault("0")
    private int level;

    @Column(nullable = false)
    private Long question_id;

    @ColumnDefault("true")
    private Boolean is_valid;

    @ColumnDefault("0")
    private Long point;

    @Temporal(value = TemporalType.DATE)
    private Date fam_anniversary;
}

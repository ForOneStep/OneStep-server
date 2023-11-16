package com.onestep.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class Family {
    @Id
    @Column(length = 100, nullable = false)
    private String fam_id;

    @Column(length = 100, nullable = false)
    private String fam_nickname;

    @ColumnDefault("0")
    private int level;

    @Column(nullable = false)
    private int fam_number;

    @ColumnDefault("1")
    private int head_count;

    @Temporal(value = TemporalType.DATE)
    private Date fam_anniversary;
}

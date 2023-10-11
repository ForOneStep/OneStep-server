package com.onestep.server.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PhotoBook {
    @Id
    @Column(nullable = false)
    private Long photo_id;

    //외래키
    @JoinColumn(name="user_id")
    private String user_id;

    @Column(nullable = false)
    private String photo_img;

    @Column(length = 1000)
    private String photo_txt;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;
}

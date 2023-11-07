package com.onestep.server.entity;

import javax.persistence.*;
import lombok.*;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long photo_id;

    //외래키
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private String photo_img;

    @Column(length = 1000)
    private String photo_txt;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;

    @OneToMany(mappedBy = "photoBook", cascade = {CascadeType.REMOVE})
    private List<PhotoBookComment> photoBookComments = new ArrayList<>();
}

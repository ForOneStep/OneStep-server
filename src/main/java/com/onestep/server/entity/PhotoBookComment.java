package com.onestep.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PhotoBookComment {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long photoBookComment_id;

    //외래키
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="photobook_id")
    private PhotoBook photoBook;

    //외래키
    @ManyToOne
    @JoinColumn(name="root_comment_id")
    private PhotoBookComment photoBookComment;

    private String comment_txt;

    //외래키
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private Date write_date;
}

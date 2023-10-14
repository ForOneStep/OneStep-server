package com.onestep.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User {
    @Id
    @Column(length = 100, nullable = false)
    private String user_id;

    //외래키
    @JoinColumn(name="fam_id")
    private String fam_id;

    @JsonIgnore
    @Column(length = 100, nullable = false)
    private String user_pwd;

    @Column(length = 100, nullable = false)
    private String user_name;

    @Column(length = 100, nullable = false)
    private String user_nickname;

    @Column(length = 100, nullable = false)
    private String user_role;

    @Column(length = 100, nullable = false)
    private String user_phone_number;

    @Temporal(value = TemporalType.DATE)
    private Date user_birth;

    private String token;

    @Column(nullable = false)
    private String profile_path;
}

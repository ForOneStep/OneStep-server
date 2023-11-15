package com.onestep.server.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDTO {
    private String user_id;
    private String user_name;
    private String user_nickname;
    private String user_role;
    private String user_phone_number;
    private Date user_birth;
    private String profile_path;
}

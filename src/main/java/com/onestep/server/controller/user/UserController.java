package com.onestep.server.controller.user;

import com.onestep.server.entity.User;
import com.onestep.server.entity.user.UserInfoDTO;
import com.onestep.server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/user/{userId}")
    public User getUserInfo(@PathVariable String userId){
        return userService.getUserInfo(userId);
    }

    // 가족id로 가족 구성원 확인
    @GetMapping(value = "/user/userInfoByFamId/{family_id}")
    public List<UserInfoDTO> getUserInfoByFamId(@PathVariable String family_id){return userService.getUserInfoByFamId(family_id);}
}

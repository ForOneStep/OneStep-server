package com.onestep.server.controller.user;

import com.onestep.server.entity.User;
import com.onestep.server.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/user/{userId}")
    public User getUserInfo(@PathVariable String userId){
        return userService.getUserInfo(userId);
    }
}

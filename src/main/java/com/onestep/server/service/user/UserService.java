package com.onestep.server.service.user;

import com.onestep.server.entity.User;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository iUserRepository;

    public User getUserInfo(String userInfo){
        return iUserRepository.findById(userInfo).get();
    }
}

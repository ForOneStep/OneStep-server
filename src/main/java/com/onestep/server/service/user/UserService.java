package com.onestep.server.service.user;

import com.onestep.server.entity.User;
import com.onestep.server.entity.user.UserInfoDTO;
import com.onestep.server.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository iUserRepository;

    public User getUserInfo(String userInfo){
        return iUserRepository.findById(userInfo).get();
    }

    //가족 id로 구성원 정보 확인

    public List<UserInfoDTO> getUserInfoByFamId(String family_id){
        List<User> users = iUserRepository.findUserByFamId(family_id);
        List<UserInfoDTO> userInfos = new ArrayList<>();
        for(User u : users){
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            userInfoDTO.setUser_id(u.getUser_id());
            userInfoDTO.setUser_name(u.getUser_name());
            userInfoDTO.setUser_nickname(u.getUser_nickname());
            userInfoDTO.setUser_birth(u.getUser_birth());
            userInfoDTO.setUser_role(u.getUser_role());
            userInfoDTO.setUser_birth(u.getUser_birth());
            userInfoDTO.setUser_phone_number(u.getUser_phone_number());

            userInfos.add(userInfoDTO);
        }

        return userInfos;
    }
}

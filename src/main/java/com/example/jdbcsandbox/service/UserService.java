package com.example.jdbcsandbox.service;


import com.example.jdbcsandbox.domain.UserDto;
import com.example.jdbcsandbox.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDto create(String name, String gender){
        return UserDto.of(userRepository.create(name,gender));
    }

    public List<UserDto> findByGender(String gender){
        return userRepository.findByGender(gender)
                .stream()
                .map(user -> UserDto.of(user))
                .collect(Collectors.toList());
    }


    //반환해줄때, User임 그러나 우리는 UserDto로 변경해서 controller에 뿌려줘야함.
    public UserDto updateName(int id, String name){
        return UserDto.of(userRepository.updateName(id,name));
    }


    //deletedYn 을 바꾸고
    // 그 User을 반환.
    public UserDto delete(int id){
        return UserDto.of(userRepository.delete(id));
    }


}

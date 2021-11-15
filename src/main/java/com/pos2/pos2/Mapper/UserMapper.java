package com.pos2.pos2.Mapper;

import com.pos2.pos2.DTO.UserDTO;
import com.pos2.pos2.DataAccess.Entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper implements MainMapper<UserDTO, User>{

    @Autowired
    private OrderMapper smallOrderMapper;

    @Override
    public UserDTO entityToDto(User user) {
        if (user == null){
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .role(user.getRole())
                .orders(
                        user.getOrders()
                                .stream()
                                .map(smallOrderMapper::entityToSmallOrder)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public User dtoToEntity(UserDTO userDTO) {
        if (userDTO == null){
            return null;
        }

        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .pass(userDTO.getPass())
                .username(userDTO.getUsername())
                .role(userDTO.getRole())
                .build();
    }
}

package com.pos2.pos2.DTO;

import com.pos2.pos2.DataAccess.Entitys.Order;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {

    private int id;

    private String name;

    private String username;

    private String pass;

    private String role;

    private List<SmallOrderDTO> orders;

    public UserDTO (String name, String username, String password, String role){

        List<SmallOrderDTO> orders = new ArrayList<>();

        this.id = 0;
        this.name = name;
        this.username = username;
        this.pass = password;
        this.role = role;
        this.orders = orders;

    }

}

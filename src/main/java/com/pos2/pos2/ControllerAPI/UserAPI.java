package com.pos2.pos2.ControllerAPI;

import com.pos2.pos2.DTO.OrderDTO;
import com.pos2.pos2.DTO.UserDTO;
import com.pos2.pos2.Exeptions.AlreadyExist;
import com.pos2.pos2.Exeptions.ElementNotFound;
import com.pos2.pos2.Service.OrderService;
import com.pos2.pos2.Service.ProductService;
import com.pos2.pos2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserAPI {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;


    //We first create all the GET requests

    //Get all orders
    @GetMapping("/getallusers")
    public List<UserDTO> getAllUsers(){
        List<UserDTO> orders = new ArrayList<>();
        orders = userService.getAll();
        return orders;
    }

    //Get one
    @GetMapping("/getuser/{id}")
    public UserDTO getUser(@PathVariable("id") Integer id) throws ElementNotFound {
        return userService.getOne(id);
    }

    //Delete a user
    @DeleteMapping("/deleteuser/{id}")
    public void deleteUserAPI (@PathVariable("id") Integer id) throws ElementNotFound{
        userService.delete(id);
    }

    //Insert a new user
    @PostMapping("/newuserapi")
    public boolean newUserAPI (@RequestBody UserDTO user) throws AlreadyExist {

        System.out.printf(user.toString());

        userService.insert(user);

        return true;

    }


}

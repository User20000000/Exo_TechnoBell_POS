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
public class OrdersAPI {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    //We first create all the GET requests

    //Get all orders
    @GetMapping("/getallorders")
    public List<OrderDTO> getAllOrders(){
        List<OrderDTO> orders = new ArrayList<>();
        orders = orderService.getAll();
        return orders;
    }

    //Get one
    @GetMapping("/getorder/{id}")
    public OrderDTO getOrder(@PathVariable("id") Integer id) throws ElementNotFound {
        return orderService.getOne(id);
    }

    //Delete a order
    @DeleteMapping("/deleteorder/{id}")
    public void deleteUserAPI (@PathVariable("id") Integer id) throws ElementNotFound{
        orderService.delete(id);
    }

    //Insert a new order
    @PostMapping("/neworderapi")
    public boolean newUserAPI (@RequestBody OrderDTO order) throws AlreadyExist {

        orderService.insert(order);

        return true;

    }


}

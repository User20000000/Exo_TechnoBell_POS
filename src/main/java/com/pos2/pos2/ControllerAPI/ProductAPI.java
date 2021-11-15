package com.pos2.pos2.ControllerAPI;

import com.pos2.pos2.DTO.ProductDTO;
import com.pos2.pos2.DTO.UserDTO;
import com.pos2.pos2.Exeptions.ElementNotFound;
import com.pos2.pos2.Service.OrderService;
import com.pos2.pos2.Service.ProductService;
import com.pos2.pos2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductAPI {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;


    //We first create all the GET requests

    //Get all orders
    @GetMapping("/getallproducts")
    public List<ProductDTO> getAllProducts(){
        List<ProductDTO> orders = new ArrayList<>();
        orders = productService.getAll();
        return orders;
    }

    //Get one
    @PostMapping("/getproduct/{id}")
    public ProductDTO getProduct(@PathVariable("id") Integer id) throws ElementNotFound {
        return productService.getOne(id);
    }


    //Update product
    @PutMapping("/updateproduct")
    public boolean updateProductAPI (@RequestBody ProductDTO product) throws ElementNotFound {
        productService.update(product);
        return true;
    }

}

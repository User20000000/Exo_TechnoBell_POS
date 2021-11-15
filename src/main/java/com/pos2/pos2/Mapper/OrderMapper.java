package com.pos2.pos2.Mapper;

import com.pos2.pos2.DTO.OrderDTO;
import com.pos2.pos2.DTO.ProductDTO;
import com.pos2.pos2.DTO.SmallOrderDTO;
import com.pos2.pos2.DTO.UserDTO;
import com.pos2.pos2.DataAccess.Entitys.Order;
import com.pos2.pos2.DataAccess.Entitys.Product;
import com.pos2.pos2.DataAccess.Entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper implements MainMapper<OrderDTO, Order>{

    @Autowired
    private MainMapper<UserDTO, User> userMapper;
    @Autowired
    private MainMapper<ProductDTO, Product> productMapper;

    @Override
    public OrderDTO entityToDto(Order order) {
        if (order == null){
            return null;
        }

        return OrderDTO.builder()
                .id(order.getId())
                .cashier( userMapper.entityToDto(order.getCashier()))
                .date(order.getDate())
                .value(order.getValue())
                .margin(order.getMargin())
                .products(
                        order.getProducts()
                                .stream()
                                .map(productMapper::entityToDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Order dtoToEntity(OrderDTO orderDTO) {
        if (orderDTO == null){
            return null;
        }

        return Order.builder()
                .id(orderDTO.getId())
                .cashier(userMapper.dtoToEntity(orderDTO.getCashier()))
                .date(orderDTO.getDate())
                .value(orderDTO.getValue())
                .margin(orderDTO.getMargin())
                .products(
                        orderDTO.getProducts()
                                .stream()
                                .map(productMapper::dtoToEntity)
                                .collect(Collectors.toList())

                )
                .build();
    }


    //The small version of Orders, to avoid an infinite loop
    public SmallOrderDTO entityToSmallOrder (Order order){

        if (order == null){
            return null;
        }

        return SmallOrderDTO.builder()
                .id(order.getId())
                .date(order.getDate())
                .value(order.getValue())
                .margin(order.getMargin())
                .build();

    }
}

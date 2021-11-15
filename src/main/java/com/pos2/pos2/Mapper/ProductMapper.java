package com.pos2.pos2.Mapper;

import com.pos2.pos2.DTO.OrderDTO;
import com.pos2.pos2.DTO.ProductDTO;
import com.pos2.pos2.DTO.SmallOrderDTO;
import com.pos2.pos2.DataAccess.Entitys.Order;
import com.pos2.pos2.DataAccess.Entitys.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper implements MainMapper<ProductDTO, Product> {

    @Autowired
    private OrderMapper smallOrderMapper;

    @Override
    public ProductDTO entityToDto(Product product) {
        if (product == null){
            return null;
        }

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .cost(product.getCost())
                .margin(product.getMargin())
                .description(product.getDescription())
                .stock(product.getStock())
                .orders(
                        product.getOrders()
                                .stream()
                                .map(smallOrderMapper::entityToSmallOrder)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Product dtoToEntity(ProductDTO productDTO) {
        if (productDTO == null){
            return null;
        }

        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .cost(productDTO.getCost())
                .margin(productDTO.getMargin())
                .description(productDTO.getDescription())
                .stock(productDTO.getStock())
                .build();
    }
}

package com.pos2.pos2.DTO;

import com.pos2.pos2.DataAccess.Entitys.Order;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {

    private int id;

    private String name;

    private Double price;

    private Double cost;

    private Double margin;

    private String description;

    private int stock;

    private List<SmallOrderDTO> orders;

    public ProductDTO (String name, Double price, Double cost, String description, int stock){

        this.id = 0;
        this.name = name;
        this.description = description;
        this.price = price;
        this.cost = cost;
        this.stock = stock;

    }

}

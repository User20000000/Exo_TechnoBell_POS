package com.pos2.pos2.DTO;

import com.pos2.pos2.DataAccess.Entitys.User;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderDTO {

    private int id;

    private UserDTO cashier;

    private Date date;

    private Double value;

    private Double margin;

    private List<ProductDTO> products;

    public OrderDTO (UserDTO cashier, Date date, Double value, Double margin){

        List<ProductDTO> products = new ArrayList<>();

        this.id = 0;
        this.date = date;
        this.value = value;
        this.margin = margin;
        this.products = products;

    }

}

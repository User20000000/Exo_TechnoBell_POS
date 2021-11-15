package com.pos2.pos2.DataAccess.Entitys;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Table(name = "product")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false, length = 22)
    private String name;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private Double price;

    @Column(name = "cost", nullable = false, precision = 10, scale = 2)
    private Double cost;

    @Column(name = "margin", nullable = false, precision = 10, scale = 2)
    private Double margin;

    @Column(name = "description", nullable = false, length = 222)
    private String description;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Order> orders;

}
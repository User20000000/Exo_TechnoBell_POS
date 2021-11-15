package com.pos2.pos2.DataAccess.Entitys;

import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "orders")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //Cashier relation ManyToOne
    @ManyToOne()
    @JoinColumn(name ="cashier")
    private User cashier;

    @Column (name ="date")
    private Date date;

    @Column (name = "value")
    private Double value;

    @Column (name = "margin")
    private Double margin;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orderproduct",
            joinColumns = @JoinColumn(name = "orderID"),
            inverseJoinColumns = @JoinColumn(name = "productID")
    )
    private List<Product> products;

}

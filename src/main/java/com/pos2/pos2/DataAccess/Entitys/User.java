package com.pos2.pos2.DataAccess.Entitys;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column (name = "username", nullable = false, unique = true)
    private String username;
    @Column (name = "pass", nullable = false)
    private String pass;
    @Column (name = "role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "cashier", fetch = FetchType.EAGER)
    private List<Order> orders;

}

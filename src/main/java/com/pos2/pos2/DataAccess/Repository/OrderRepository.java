package com.pos2.pos2.DataAccess.Repository;

import com.pos2.pos2.DataAccess.Entitys.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

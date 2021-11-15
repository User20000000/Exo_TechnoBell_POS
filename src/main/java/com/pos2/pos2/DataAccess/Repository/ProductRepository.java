package com.pos2.pos2.DataAccess.Repository;

import com.pos2.pos2.DataAccess.Entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Integer> {
}

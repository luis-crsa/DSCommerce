package com.luiscrsarmento.ecommerce.repositories;

import com.luiscrsarmento.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}

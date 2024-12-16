package com.luiscrsarmento.ecommerce.repositories;

import com.luiscrsarmento.ecommerce.entities.OrderItem;
import com.luiscrsarmento.ecommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{
}

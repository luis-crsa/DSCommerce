package com.luiscrsarmento.ecommerce.services;

import com.luiscrsarmento.ecommerce.dto.OrderDTO;
import com.luiscrsarmento.ecommerce.dto.OrderItemDTO;
import com.luiscrsarmento.ecommerce.entities.Order;
import com.luiscrsarmento.ecommerce.entities.OrderItem;
import com.luiscrsarmento.ecommerce.entities.OrderStatus;
import com.luiscrsarmento.ecommerce.entities.Product;
import com.luiscrsarmento.ecommerce.repositories.OrderItemRepository;
import com.luiscrsarmento.ecommerce.repositories.OrderRepository;
import com.luiscrsarmento.ecommerce.repositories.ProductRepository;
import com.luiscrsarmento.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Optional<Order> result = orderRepository.findById(id);
        Order order = result.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {

        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());

        for(OrderItemDTO itemDto : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}

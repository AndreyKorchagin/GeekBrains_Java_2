package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.repositories.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void saveOrUpdateOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public OrderItem findOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrderItem с таким id отсутствует в базе данных"));
    }
}

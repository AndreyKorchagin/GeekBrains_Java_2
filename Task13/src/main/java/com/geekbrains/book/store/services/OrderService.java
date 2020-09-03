package com.geekbrains.book.store.services;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UserService userService;
    private OrderItemService orderItemService;
    private Cart cart;

    @Autowired
    public void setOrdersRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order findOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Transactional
    public Order processOrder(String userName){
        User user = userService.findByUsername(userName).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getPrice());
        order.setOrderStatus(Order.OrderStatus.PROCESSING);
        order = saveOrUpdateOrder(order);
        for (OrderItem orderItem : cart.getItems()) {
            orderItem.setOrder(order);
            orderItemService.saveOrUpdateOrderItem(orderItem);
        }
        cart.clear();

        return order;
    }


}

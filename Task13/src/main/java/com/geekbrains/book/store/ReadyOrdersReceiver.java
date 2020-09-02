package com.geekbrains.book.store;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReadyOrdersReceiver {
    private RabbitTemplate rabbitTemplate;
    private OrderService orderService;

    public void receiveMessage(byte[] message) {
        Long orderId = Long.parseLong(new String(message));
        System.out.println("Заказ №" + orderId.toString() + " готов");
        Order order = orderService.findOrderById(orderId);
        order.setOrderStatus(Order.OrderStatus.READY);
        orderService.saveOrUpdateOrder(order);
    }
}

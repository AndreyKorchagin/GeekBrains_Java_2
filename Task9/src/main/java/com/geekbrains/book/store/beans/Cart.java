package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.services.OrderItemService;
import com.geekbrains.book.store.services.OrderService;
import com.geekbrains.book.store.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {
    private List<OrderItem> orderItems;
    private BigDecimal totalPrice;
    private BookService bookService;
    private UserService userService;
    private OrderService orderService;
    private OrderItemService orderItemService;

    @Autowired
    public Cart(BookService bookService, UserService userService, OrderService orderService, OrderItemService orderItemService) {
        this.bookService = bookService;
        this.userService = userService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.orderItems = new ArrayList<>();
        this.totalPrice = new BigDecimal("0.0");
    }

    public void addBookToCart(Long id) {
        Book book = bookService.findBookById(id);
        OrderItem orderItem = searchOrderItemByBook(book);
        if (orderItem != null) {
            orderItem.setCount(orderItem.getCount() + 1);
            orderItem.setPrice(orderItem.getPrice().add(book.getPrice()));
        } else {
            orderItem = new OrderItem(1L, book.getPrice(), book);
            orderItems.add(orderItem);
        }
        totalPrice = totalPrice.add(book.getPrice());
    }

    private OrderItem searchOrderItemByBook(Book book) {
        OrderItem result = null;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getBook().equals(book)) {
                result = orderItem;
                break;
            }
        }
        return result;
    }

    public void deleteBookFromCart(int index) {
        OrderItem orderItem = orderItems.get(index - 1);
        Book book = orderItem.getBook();
        if (orderItem.getCount() > 1) {
            orderItem.setCount(orderItem.getCount() - 1);
            orderItem.setPrice(orderItem.getPrice().subtract(book.getPrice()));
        } else {
            orderItems.remove(orderItem);
        }
        totalPrice = totalPrice.subtract(orderItem.getBook().getPrice());
    }

    public void clearCart() {
        orderItems.clear();
        totalPrice = new BigDecimal("0.0");
    }

    public Order processOrder(String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", username)));
        Order order = new Order();
        order.setUser(user);
        order.setPrice(totalPrice);
        order = orderService.saveOrUpdateOrder(order);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            orderItemService.saveOrUpdateOrderItem(orderItem);
        }
        clearCart();
        return order;
    }
}
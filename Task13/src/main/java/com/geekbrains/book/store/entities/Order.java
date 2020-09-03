package com.geekbrains.book.store.entities;

import com.geekbrains.book.store.beans.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    public enum OrderStatus {
        PROCESSING("В обработке"),
        READY("Готово");

        private final String statusName;

        OrderStatus(String statusName) {
            this.statusName = statusName;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;

    public Order(User user, Cart cart) {
        this.user = user;
        this.items = new ArrayList<>();
        for (OrderItem oi : cart.getItems()) {
            oi.setOrder(this);
            this.items.add(oi);
        }
        this.price = new BigDecimal(cart.getPrice().doubleValue());
        cart.clear();
    }
}

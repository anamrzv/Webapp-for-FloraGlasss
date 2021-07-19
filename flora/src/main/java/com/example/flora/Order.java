package com.example.flora;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "florarium")
    private int florariumId;
    @Column(name = "customer")
    private String customer;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "delivery_date")
    private LocalDateTime deliveryDateTime;
    @Column(name = "comments")
    private String comments;

    @Override
    public String toString() {
        return "Заказ {" +
                "id=" + id +
                ", номер флорариума=" + florariumId +
                ", заказчик='" + customer + '\'' +
                ", дата заказа=" + orderDate +
                ", дата и время доставки=" + deliveryDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && florariumId == order.florariumId && customer.equals(order.customer) && orderDate.equals(order.orderDate) && deliveryDateTime.equals(order.deliveryDateTime) && Objects.equals(comments, order.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, florariumId, customer, orderDate, deliveryDateTime, comments);
    }
}

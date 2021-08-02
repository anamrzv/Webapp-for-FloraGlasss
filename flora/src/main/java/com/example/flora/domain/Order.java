package com.example.flora.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Column(name = "month_num")
    private int numInThisMonth;
    @Column
    private int price;
    @Column(name = "customer")
    private String customer;
    @Column
    private String phone;
    @Column
    private String address;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column
    private String source;
    @Column(name = "comment")
    private String comments;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Override
    public String toString() {
        return "Заказ {" +
                "id=" + id +
                ", заказчик='" + customer + '\'' +
                ", дата заказа=" + orderDate +
                ", дата и время доставки=" + deliveryDate +
                '}';
    }
}

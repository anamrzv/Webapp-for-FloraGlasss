package com.example.flora.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
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

    public Order(String customer, int price, String phone, String address, LocalDate orderDate, LocalDate deliveryDate, String source, String comments) {
        this.customer = customer;
        this.address = address;
        this.comments = comments;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.source = source;
        this.phone = phone;
        this.price = price;
    }

    public static class Builder{
        private Order newOrder;

        public Builder(){
            newOrder = new Order();
        }

        public Builder withCustomer(String customer){
            newOrder.customer=customer;
            return this;
        }

        public Builder withAddress(String address){
            newOrder.address=address;
            return this;
        }

        public Builder withComments(String comments){
            newOrder.comments=comments;
            return this;
        }

        public Builder withOrderDate(LocalDate orderDate){
            newOrder.orderDate=orderDate;
            return this;
        }

        public Builder withDeliveryDate(LocalDate deliveryDate){
            newOrder.deliveryDate=deliveryDate;
            return this;
        }

        public Builder withSource(String source){
            newOrder.source=source;
            return this;
        }

        public Builder withPhone(String phone){
            newOrder.phone=phone;
            return this;
        }

        public Builder withPrice(int price){
            newOrder.price=price;
            return this;
        }

        public Builder withId(int id){
            newOrder.id=id;
            return this;
        }

        public Builder withMonthId(int num){
            newOrder.numInThisMonth=num;
            return this;
        }

        public Order build(){
            return newOrder;
        }

    }

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

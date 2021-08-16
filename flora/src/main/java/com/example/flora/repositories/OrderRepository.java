package com.example.flora.repositories;

import com.example.flora.domain.Order;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order,Long> {

    @Query(value = "SELECT * FROM ORDERS", nativeQuery = true)
    public LinkedList<Order> getListOfOrders();

    @Query(value = "select * from orders where extract(month from order_date)=extract(month from current_date) ORDER BY MONTH_NUM DESC", nativeQuery = true)
    public LinkedList<Order> getSortedOrdersInThisMonth();

    @Query(value = "select max(month_num) from orders where extract(month from order_date)=extract(month from current_date)", nativeQuery = true)
    public int getLastNumInThisMonth();

    @Query(value = "SELECT max(month_num) FROM orders WHERE EXTRACT(MONTH FROM order_date)=EXTRACT(MONTH FROM cast(:date as DATE)) AND EXTRACT(YEAR FROM order_date)=EXTRACT(YEAR FROM cast(:date as DATE))", nativeQuery = true)
    public Integer getLastNumInMonth(@Param("date") LocalDate date);

}

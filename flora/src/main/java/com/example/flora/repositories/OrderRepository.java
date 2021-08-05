package com.example.flora.repositories;

import com.example.flora.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;

public interface OrderRepository extends CrudRepository<Order,Long> {

    @Query(value = "SELECT * FROM ORDERS", nativeQuery = true)
    public LinkedList<Order> getListOfOrders();
}

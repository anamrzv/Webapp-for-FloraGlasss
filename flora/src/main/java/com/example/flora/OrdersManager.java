package com.example.flora;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OrdersManager {
    public void save(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession(); //открываем сессию
        session.beginTransaction();
        session.save(order); //пользуемся ее методами
        session.flush();
        session.close();
    }

    public void delete(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(order);
        session.flush();
        session.close();
    }

    public List<Order> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        criteria.from(Order.class);
        List<Order> orders = session.createQuery(criteria).getResultList();
        session.close();
        return orders;
    }

    public Order getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Order order = session.get(Order.class, id);
        return order;
    }
}

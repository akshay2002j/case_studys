package com.example.inventory_system.dao;

import com.example.inventory_system.entity.Product;
import com.example.inventory_system.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Override
    public void saveProduct(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
        }
    }

    @Override
    public Product getProductById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.getReference(Product.class, id);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("from Product", Product.class);
            return query.list();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Product p = session.getReference(Product.class, id);
            if (p != null) session.detach(p);
            tx.commit();
        }
    }

    @Override
    public List<Product> findLowStockProducts(int threshold) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery(
                    "FROM Product p WHERE p.quantity < :threshold", Product.class);
            query.setParameter("threshold", threshold);
            return query.list();
        }
    }

    @Override
    public Double getTotalInventoryValue() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Double> query = session.createQuery(
                    "SELECT SUM(p.price * p.quantity) FROM Product p", Double.class);
            return query.uniqueResult();
        }
    }
}

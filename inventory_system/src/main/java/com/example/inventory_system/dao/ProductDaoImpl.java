package com.example.inventory_system.dao;

import com.example.inventory_system.entity.Product;
import com.example.inventory_system.exception.DBException;
import com.example.inventory_system.exception.ExceptionType;
import com.example.inventory_system.util.HibernateUtil;
import org.hibernate.HibernateException;
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
        catch (HibernateException e) {
            throw new DBException(ExceptionType.DB_EXCEPTION);
        }
    }

    @Override
    public Product getProductById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Product product =  session.getReference(Product.class, id);
            Product product1 = new Product();
            product1.setId(product.getId());
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setQuantity(product.getQuantity());
            tx.commit();
            return product1;
        }
        catch (HibernateException e) {
            throw new DBException(ExceptionType.DB_EXCEPTION);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Product> products = session.createSelectionQuery("from Product", Product.class).list();
            return products;
        }
        catch (HibernateException e) {
            throw new DBException(ExceptionType.DB_EXCEPTION);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
        }
        catch (HibernateException e) {
            throw new DBException(ExceptionType.DB_EXCEPTION);
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
        catch (HibernateException e) {
            throw new DBException(ExceptionType.DB_EXCEPTION);
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
        catch (HibernateException e) {
            throw new DBException(ExceptionType.DB_EXCEPTION);
        }
    }

    @Override
    public Double getTotalInventoryValue() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Double> query = session.createQuery(
                    "SELECT SUM(p.price * p.quantity) FROM Product p", Double.class);
            return query.getSingleResult();
        }
        catch (HibernateException e) {
            throw new DBException(ExceptionType.DB_EXCEPTION);
        }
    }
}

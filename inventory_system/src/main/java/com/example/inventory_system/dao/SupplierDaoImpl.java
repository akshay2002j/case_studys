package com.example.inventory_system.dao;

import com.example.inventory_system.entity.Supplier;
import com.example.inventory_system.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SupplierDaoImpl implements SupplierDao{

    @Override
    public void saveSupplier(Supplier supplier) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(supplier);
            tx.commit();
        }
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(supplier);
            tx.commit();
        }
    }

    @Override
    public void deleteSupplier(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Supplier supplier = session.getReference(Supplier.class, id);
            if (supplier != null) {
                session.detach(supplier);
            }
            tx.commit();
        }
    }

    @Override
    public Supplier getSupplierById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.getReference(Supplier.class, id);
        }
    }

    @Override
    public Supplier findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Supplier> query = session.createQuery(
                    "FROM Supplier s WHERE s.name = :name", Supplier.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        }
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Supplier", Supplier.class).list();
        }
    }

    // HQL query to get supplier name + number of products supplied
    @Override
    public List<Object[]> getSupplierProductCount() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Object[]> query = session.createQuery(
                    "SELECT s.name, COUNT(p.id) FROM Supplier s JOIN s.products p GROUP BY s.name",
                    Object[].class
            );
            return query.list();
        }
    }
}

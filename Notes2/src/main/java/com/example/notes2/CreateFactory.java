package com.example.notes2;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class CreateFactory {
    private static SessionFactory sessionF;
    private CreateFactory() {}

    public static <T> List<T> loadAllData(Class<T> type, Session session) {
        System.out.println("Printing all table...");
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return session.createQuery(criteria).getResultList();
    }


    public static SessionFactory getFactory() {
        if (sessionF == null) {
            Configuration configuration = new Configuration().configure();
            System.out.println("Hibernate Annotation Configuration loaded");
            configuration.addAnnotatedClass(Notes.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            System.out.println("Hibernate Annotation serviceRegistry created");
            sessionF = configuration.buildSessionFactory(builder.build());
        }
        return sessionF;
    }

    public static void close() {
        sessionF.close();
    }


}

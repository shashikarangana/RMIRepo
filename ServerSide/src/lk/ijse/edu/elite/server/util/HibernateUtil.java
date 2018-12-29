package lk.ijse.edu.elite.server.util;

import lk.ijse.edu.elite.server.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory=buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        StandardServiceRegistry registry=new StandardServiceRegistryBuilder().loadProperties("/EliteSettings.properties").build();

        Metadata metadata=new MetadataSources(registry).
                addAnnotatedClass(Customer.class).
                addAnnotatedClass(Orders.class).
                addAnnotatedClass(Foods.class).
                addAnnotatedClass(Reception.class).
                addAnnotatedClass(Chef.class).
                addAnnotatedClass(Delivery.class).
                addAnnotatedClass(UserAccount.class).
                addAnnotatedClass(OrderDetails.class).buildMetadata();

        return metadata.getSessionFactoryBuilder().build();
    }
    public static SessionFactory getSessionFactory(){return sessionFactory;}
}

package util.validation;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfigeration {
    private static FactoryConfigeration factoryConfigeration;
    private SessionFactory sessionFactory;

    private FactoryConfigeration(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(Item.class)
                .addAnnotatedClass(Orders.class).addAnnotatedClass(OrderDetail.class).addAnnotatedClass(Login.class);
        sessionFactory=configuration.buildSessionFactory();

    }
    public static FactoryConfigeration getInstance(){
        return (factoryConfigeration==null)? factoryConfigeration=new FactoryConfigeration() : factoryConfigeration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}

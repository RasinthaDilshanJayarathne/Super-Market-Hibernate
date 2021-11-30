package dao.impl;

import dao.custom.CustomerDAO;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.validation.FactoryConfigeration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
/*
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)", customer.getId(), customer.getTitle(),customer.getName(),customer.getAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode());
*/
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(customer);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
/*
        return CrudUtil.executeUpdate("UPDATE Customer SET CustTitle=?, CustName=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustId=?",customer.getTitle(),customer.getName(),customer.getAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode(), customer.getId());
*/
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(customer);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
/*
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE CustId=?", s);
*/
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(s);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        /*ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE CustId=?", id);
        rst.next();
        return new Customer(id,
                rst.getString("CustTitle"),
                rst.getString("CustName"),
                rst.getString("CustAddress"),
                rst.getString("City"),
                rst.getString("Province"),
                rst.getString("PostalCode"));*/
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, id);
        transaction.commit();
        session.close();
        return customer;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        /*ArrayList<Customer> allCustomers = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        while (rst.next()) {
            allCustomers.add(new Customer(
                    rst.getString("CustId"),
                    rst.getString("CustTitle"),
                    rst.getString("CustName"),
                    rst.getString("CustAddress"),
                    rst.getString("City"),
                    rst.getString("Province"),
                    rst.getString("PostalCode"))
            );
        }
        return allCustomers;*/
        ArrayList<Customer> allCustomers = new ArrayList();
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Customer");
        allCustomers = (ArrayList<Customer>) query.list();
        transaction.commit();
        session.close();
        return allCustomers;
    }

    @Override
    public boolean ifCustomerExist(String id) throws SQLException, ClassNotFoundException {
/*
        return CrudUtil.executeQuery("SELECT CustId FROM Customer WHERE CustId=?", id).next();
*/
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT id FROM Customer WHERE id=:id");
        query.setParameter("id",id);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        /*ResultSet rst = CrudUtil.executeQuery("SELECT CustId FROM Customer ORDER BY CustId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("CustId");
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C%03d", newCustomerId);
        } else {
            return "C001";
        }*/
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        if (id!=null) {
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            id = String.format("C%03d", newCustomerId);

        } else {
            return "C001";
        }
        transaction.commit();
        session.close();
        return id;
    }
}

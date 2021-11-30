package dao.impl;

import dao.custom.OrderDAO;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.validation.FactoryConfigeration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Orders order) throws SQLException, ClassNotFoundException {
/*
        return CrudUtil.executeUpdate("INSERT INTO Orders VALUES (?,?,?,?,?)", order.getOrderId(), order.getOrderDate(), order.getOrderTime(), order.getCustomerId(), order.getCoust());
*/
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(order);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Orders order) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
/*
        throw new UnsupportedOperationException("Not Supported Yet");
*/
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(s);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Orders search(String s) throws SQLException, ClassNotFoundException {
        /*ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Orders WHERE OrderId=?", s);
        rst.next();
        return new Order(
                rst.getString("OrderId"),
                LocalDate.parse(rst.getString("OrderDate")),
                LocalTime.parse(rst.getString("OrderTime")),
                rst.getString("CustId"),
                rst.getDouble("Cost")
        );*/
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query s1 = session.createQuery("FROM Orders WHERE orderId=:s").setParameter("s", s);
        List<Orders> list = s1.list();
        transaction.commit();
        session.close();

        Orders orders = null;
        for (Orders order:list) {
            orders=new Orders(
                    order.getOrderId(),order.getOrderDate(),order.getOrderTime(),order.getCoust(),order.getCustomer()
            );
        }
        return orders;
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        /*ArrayList<Order> allOrders = new ArrayList();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Orders");
        while (rst.next()) {
            allOrders.add(new Order(
                    rst.getString("OrderId"),
                    LocalDate.parse(rst.getString("OrderDate")),
                    LocalTime.parse(rst.getString("OrderTime")),
                    rst.getString("CustId"),
                    rst.getDouble("Cost"))
            );
        }
        return allOrders;*/
        ArrayList<Orders> allOrders = new ArrayList();

        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query order = session.createQuery("FROM Orders ");
        allOrders = (ArrayList<Orders>) order.list();
        transaction.commit();
        session.close();
        return allOrders;
    }

    @Override
    public boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException {
        /*ResultSet rst = CrudUtil.executeQuery("SELECT OrderId FROM Orders WHERE OrderId=?", oid);
        return rst.next();*/

        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT orderId FROM Orders WHERE orderId=:oid");
        String id1 = (String) query.setParameter("oid", oid).uniqueResult();
        if (id1!=null){
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        /*ResultSet rst = CrudUtil.executeQuery("SELECT OrderId FROM Orders ORDER BY OrderId DESC LIMIT 1;");
        return rst.next() ? String.format("OD%03d", (Integer.parseInt(rst.getString("OrderId").replace("OD", "")) + 1)) : "OD001";*/

        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1");
        String s = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        if (s!=null) {
            int orderId = Integer.parseInt(s.replace("O", "")) + 1;
            return String.format("O%03d", orderId);
        }
        return "O001";
    }
}

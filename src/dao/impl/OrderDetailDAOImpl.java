package dao.impl;

import dao.custom.OrderDetailDAO;
import entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.validation.FactoryConfigeration;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean add(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
/*
        return CrudUtil.executeUpdate("INSERT INTO OrderDetail VALUES (?,?,?,?)", orderDetail.getOrderId(), orderDetail.getItemCode(), orderDetail.getOrderQty(), orderDetail.getDiscount());
*/
        Session session = FactoryConfigeration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(orderDetail);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }
}

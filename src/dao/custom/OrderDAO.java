package dao.custom;

import entity.Orders;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Orders,String> {
    boolean ifOrderExist(String oid) throws SQLException, ClassNotFoundException;
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
}

package lk.ijse.edu.elite.server.dao.dao.custom;

import lk.ijse.edu.elite.server.dao.CrudDAO;
import lk.ijse.edu.elite.server.entity.Orders;

import java.util.List;

public interface OrderDAO extends CrudDAO<Orders,String> {
    public List<Orders> getAllAvailableOrders() throws Exception ;

    public List<Orders> getAllAvailableOrdersForDeliver() throws Exception ;
}

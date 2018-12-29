package lk.ijse.edu.elite.server.dao.dao.custom;

import lk.ijse.edu.elite.common.dto.OrdersDTO;
import lk.ijse.edu.elite.server.dao.SuperDAO;
import lk.ijse.edu.elite.server.entity.Customer;
import lk.ijse.edu.elite.server.entity.OrderDetails;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public Customer getCustomerByOrderID(String id)throws Exception;

    public ArrayList<OrderDetails> getOrderDetailByID(String id)throws Exception;

    public ArrayList<OrdersDTO> getOrdersByCustomerID(String id)throws Exception;
}

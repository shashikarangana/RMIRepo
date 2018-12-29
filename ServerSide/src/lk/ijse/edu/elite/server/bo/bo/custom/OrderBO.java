package lk.ijse.edu.elite.server.bo.bo.custom;

import lk.ijse.edu.elite.common.dto.OrdersDTO;
import lk.ijse.edu.elite.server.bo.SuperBO;

import java.util.List;

public interface OrderBO extends SuperBO {
    public boolean saveOrder(OrdersDTO orderDTO) throws Exception;

    public boolean saveOrderWithoutCustomer(OrdersDTO orderDTO) throws Exception;

    public boolean updateOrderss(OrdersDTO orderDTO) throws Exception;

    public boolean updateChef_order_end(OrdersDTO orderDTO) throws Exception;

    public boolean updateDeliver_order_end(OrdersDTO orderDTO) throws Exception;

    public boolean updateOrderDelivery(OrdersDTO orderDTO) throws Exception;

    public boolean deleteOrderss(OrdersDTO ordersDTO)throws Exception;

    public OrdersDTO searchOrders(String id)throws Exception;

    public List<OrdersDTO> getAll()throws Exception;

    public List<OrdersDTO> getAllOrdersByCustomerID(String id)throws Exception;

    public List<OrdersDTO> getAllAvailableOrders()throws Exception;

    public List<OrdersDTO> getAllAvailableOrdersForDeliver()throws Exception;
}

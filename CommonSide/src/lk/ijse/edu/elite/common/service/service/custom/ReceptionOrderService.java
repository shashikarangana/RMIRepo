package lk.ijse.edu.elite.common.service.service.custom;

import lk.ijse.edu.elite.common.dto.OrderDetailTableDTO;
import lk.ijse.edu.elite.common.dto.OrdersDTO;
import lk.ijse.edu.elite.common.observer.Subject;
import lk.ijse.edu.elite.common.reservation.Reservation;
import lk.ijse.edu.elite.common.service.SuperService;

import java.util.ArrayList;

public interface ReceptionOrderService extends SuperService ,Reservation,Subject {
    public boolean addOrder(OrdersDTO chefDTO) throws Exception;

    public boolean addOrderWithoyCustomer(OrdersDTO ordersDTO) throws Exception;

    public boolean updateOrder(OrdersDTO chefDTO) throws Exception;

    public boolean updateOrderAsDeliverd(OrdersDTO chefDTO) throws Exception;

    public boolean updateChef_end_time(OrdersDTO ordersDTO) throws Exception;

    public boolean updateDeliver_end_time(OrdersDTO ordersDTO) throws Exception;

    public boolean deleteOrder(OrdersDTO ordersDTO) throws Exception;

    public OrdersDTO searchOrder(String id) throws Exception;

    public ArrayList<OrderDetailTableDTO> getOrderDetailByID(String id) throws Exception;

    public ArrayList<OrdersDTO> getOrdersByID(String custID) throws Exception;

    public ArrayList<OrdersDTO> getAvailableOrder() throws Exception;

    public ArrayList<OrdersDTO> getDeliverOrders() throws Exception;

    public ArrayList<OrdersDTO> getAll() throws Exception;
}

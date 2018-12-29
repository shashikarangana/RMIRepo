package lk.ijse.edu.elite.server.service.impl;

import lk.ijse.edu.elite.common.dto.OrderDetailTableDTO;
import lk.ijse.edu.elite.common.dto.OrdersDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.service.custom.ReceptionOrderService;
import lk.ijse.edu.elite.server.bo.BOFactory;
import lk.ijse.edu.elite.server.bo.bo.custom.OrderBO;
import lk.ijse.edu.elite.server.bo.bo.custom.OrderDetailBO;
import lk.ijse.edu.elite.server.bo.bo.custom.impl.OrderBOImpl;
import lk.ijse.edu.elite.server.bo.bo.custom.impl.OrderDetailBOImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ReceptionOrderServiceImpl extends UnicastRemoteObject implements ReceptionOrderService {
    private OrderBO orderBO;
    private OrderDetailBO orderDetailBO;

    public ReceptionOrderServiceImpl() throws RemoteException {
        orderBO =new OrderBOImpl();
        orderDetailBO = new OrderDetailBOImpl();
    }

    @Override
    public boolean addOrder(OrdersDTO ordersDTO) throws Exception {
        System.out.println("Service " + ordersDTO.getTotal_price());
        return orderBO.saveOrder(ordersDTO);

    }

    @Override
    public boolean addOrderWithoyCustomer(OrdersDTO ordersDTO) throws Exception {
        return orderBO.saveOrderWithoutCustomer(ordersDTO);
    }

    @Override
    public boolean updateOrder(OrdersDTO ordersDTO) throws Exception {
        return orderBO.updateOrderss(ordersDTO);
    }

    @Override
    public boolean updateOrderAsDeliverd(OrdersDTO chefDTO) throws Exception {
        return orderBO.updateOrderDelivery(chefDTO);
    }

    @Override
    public boolean updateChef_end_time(OrdersDTO ordersDTO) throws Exception {
        return orderBO.updateChef_order_end(ordersDTO);
    }

    @Override
    public boolean updateDeliver_end_time(OrdersDTO ordersDTO) throws Exception {
        return orderBO.updateDeliver_order_end(ordersDTO);
    }



    @Override
    public boolean deleteOrder(OrdersDTO ordersDTO) throws Exception {
        return orderBO.deleteOrderss(ordersDTO);
    }

    @Override
    public OrdersDTO searchOrder(String id) throws Exception {
        return orderBO.searchOrders(id);
    }

    @Override
    public ArrayList<OrderDetailTableDTO> getOrderDetailByID(String id) throws Exception {
        return orderDetailBO.searchOrderDetail(id);
    }

    @Override
    public ArrayList<OrdersDTO> getOrdersByID(String custID) throws Exception {
        List<OrdersDTO> allOrdersByCustomerID = orderBO.getAllOrdersByCustomerID(custID);
        ArrayList<OrdersDTO> dtos = new ArrayList<>();
        for (OrdersDTO a : allOrdersByCustomerID) {
            dtos.add(new OrdersDTO(a.getOrder_ID(), a.getOrder_date(), a.getOrder_time(), a.getStatus(), a.getPaymentStatus(), a.isHomeDelivery(), a.getTotal_price(), a.getReception_id(), a.getChef_id(), a.getRider_id(), a.getChef_order_start(), a.getChef_order_end(), a.getDeliver_order_start(), a.getDeliver_order_end()));
        }
        return dtos;
    }

    @Override
    public ArrayList<OrdersDTO> getAvailableOrder() throws Exception {
        List<OrdersDTO> allAvailableOrders = orderBO.getAllAvailableOrders();
        ArrayList<OrdersDTO> dtos = new ArrayList<>();
        for (OrdersDTO a : allAvailableOrders) {
            dtos.add(new OrdersDTO(a.getOrder_ID(), a.getOrder_date(), a.getOrder_time(), a.getStatus(), a.getPaymentStatus(), a.isHomeDelivery(), a.getTotal_price(), a.getOrderDetailDTOS(), a.getReception_id()));
        }
        return dtos;
    }

    @Override
    public ArrayList<OrdersDTO> getDeliverOrders() throws Exception {
        List<OrdersDTO> allAvailableOrders = orderBO.getAllAvailableOrdersForDeliver();
        ArrayList<OrdersDTO> dtos = new ArrayList<>();
        for (OrdersDTO a : allAvailableOrders) {
            dtos.add(new OrdersDTO(a.getOrder_ID(), a.getOrder_date(), a.getOrder_time(), a.getStatus(), a.getPaymentStatus(), a.isHomeDelivery(), a.getTotal_price(), a.getOrderDetailDTOS(), a.getReception_id()));
        }
        return dtos;
    }

    @Override
    public ArrayList<OrdersDTO> getAll() throws Exception {
        return null;
    }

    @Override
    public void register(Observer observer) throws Exception {

    }

    @Override
    public void unregister(Observer observer) throws Exception {

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {

    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return false;
    }

    @Override
    public boolean released(Object id) throws Exception {
        return false;
    }
}

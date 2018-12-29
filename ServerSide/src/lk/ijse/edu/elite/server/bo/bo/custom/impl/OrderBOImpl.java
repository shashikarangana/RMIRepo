package lk.ijse.edu.elite.server.bo.bo.custom.impl;

import lk.ijse.edu.elite.common.dto.OrderDetailDTO;
import lk.ijse.edu.elite.common.dto.OrdersDTO;
import lk.ijse.edu.elite.server.bo.bo.custom.OrderBO;
import lk.ijse.edu.elite.server.dao.DAOFactory;
import lk.ijse.edu.elite.server.dao.dao.custom.OrderDAO;
import lk.ijse.edu.elite.server.dao.dao.custom.QueryDAO;
import lk.ijse.edu.elite.server.entity.*;
import lk.ijse.edu.elite.server.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    private OrderDAO orderDAO;
    private QueryDAO queryDAO;
    private SessionFactory sessionFactory;

    public OrderBOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        orderDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERS);
        queryDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    @Override
    public boolean saveOrder(OrdersDTO orderDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Customer customer = new Customer();
            customer.setCust_id(orderDTO.getCustomerDTO().getCust_id());
            customer.setCust_name(orderDTO.getCustomerDTO().getCust_name());
            customer.setContact(orderDTO.getCustomerDTO().getContact());
            customer.setNic(orderDTO.getCustomerDTO().getNic());
            customer.setAddres(orderDTO.getCustomerDTO().getAddres());
            customer.setDob(orderDTO.getCustomerDTO().getDob());

            Chef chef = session.get(Chef.class, "Ch001");
            Reception reception = session.get(Reception.class, orderDTO.getReception_id());
            Delivery delivery = session.get(Delivery.class, "B001");

            Orders orders = new Orders();
            orders.setOrder_ID(orderDTO.getOrder_ID());
            orders.setOrder_date(orderDTO.getOrder_date());
            orders.setOrder_time(orderDTO.getOrder_time());
            System.out.println("BOImpl " + orderDTO.getTotal_price());
            orders.setTotal_price(orderDTO.getTotal_price());
            orders.setPaymentStatus(orderDTO.getPaymentStatus());
            orders.setHomeDelivery(orderDTO.isHomeDelivery());
            orders.setPaymentStatus(orderDTO.getPaymentStatus());
            orders.setStatus(orderDTO.getStatus());
            orders.setCustomer(customer);
            orders.setChef(chef);
            orders.setDelivery(delivery);
            orders.setReception(reception);

            session.persist(orders);

            List<OrderDetails> orderDetails = new ArrayList<>();
            for (OrderDetailDTO o : orderDTO.getOrderDetailDTOS()) {
                Foods foods = session.get(Foods.class, o.getFood_code());

                OrderDetails orderD = new OrderDetails();
                orderD.setUnitPrice(o.getUnitPrice());
                orderD.setOrderd_Qty(o.getOrderdQty());
                orderD.setFoods(foods);
                orderD.setOrders(orders);

                session.persist(orderD);
            }

            session.getTransaction().commit();
            return true;
        }


    }

    @Override
    public boolean saveOrderWithoutCustomer(OrdersDTO orderDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Customer customer = session.get(Customer.class, orderDTO.getCustomerDTO().getCust_id());

            Chef chef = session.get(Chef.class, "CH001");
            Reception reception = session.get(Reception.class, orderDTO.getReception_id());
            Delivery delivery = session.get(Delivery.class, "B001");

            Orders orders = new Orders();
            orders.setOrder_ID(orderDTO.getOrder_ID());
            orders.setOrder_date(orderDTO.getOrder_date());
            orders.setOrder_time(orderDTO.getOrder_time());
            System.out.println("BOImpl " + orderDTO.getTotal_price());
            orders.setTotal_price(orderDTO.getTotal_price());
            orders.setPaymentStatus(orderDTO.getPaymentStatus());
            orders.setHomeDelivery(orderDTO.isHomeDelivery());
            orders.setPaymentStatus(orderDTO.getPaymentStatus());
            orders.setStatus(orderDTO.getStatus());
            orders.setCustomer(customer);
            orders.setChef(chef);
            orders.setDelivery(delivery);
            orders.setReception(reception);

            session.persist(orders);

            List<OrderDetails> orderDetails = new ArrayList<>();
            for (OrderDetailDTO o : orderDTO.getOrderDetailDTOS()) {
                Foods foods = session.get(Foods.class, o.getFood_code());

                OrderDetails orderD = new OrderDetails();
                orderD.setUnitPrice(o.getUnitPrice());
                orderD.setOrderd_Qty(o.getOrderdQty());
                orderD.setFoods(foods);
                orderD.setOrders(orders);

                session.persist(orderD);
            }

            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean updateOrderss(OrdersDTO orderDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Orders orders = session.get(Orders.class, orderDTO.getOrder_ID());
            orders.setStatus(orderDTO.getStatus());
            orders.setChef_order_start(orderDTO.getChef_order_start());
            Chef chef = new Chef();
            chef.setChef_ID(orderDTO.getChef_id());
            orders.setChef(chef);

            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean updateChef_order_end(OrdersDTO orderDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Orders orders = session.get(Orders.class, orderDTO.getOrder_ID());
            orders.setStatus(orderDTO.getStatus());
            orders.setChef_order_end(orderDTO.getChef_order_end());
            Chef chef = new Chef();
            chef.setChef_ID(orderDTO.getChef_id());
            orders.setChef(chef);

            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean updateDeliver_order_end(OrdersDTO orderDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Orders orders = session.get(Orders.class, orderDTO.getOrder_ID());
            orders.setStatus(orderDTO.getStatus());
            orders.setPaymentStatus(orderDTO.getPaymentStatus());
            orders.setDeliver_order_end(orderDTO.getDeliver_order_end());
            Delivery delivery = new Delivery();
            delivery.setDeliverID(orderDTO.getRider_id());
            orders.setDelivery(delivery);

            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean updateOrderDelivery(OrdersDTO orderDTO) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Orders orders = session.get(Orders.class, orderDTO.getOrder_ID());
            orders.setStatus(orderDTO.getStatus());
            orders.setPaymentStatus(orderDTO.getPaymentStatus());
            orders.setDeliver_order_start(orderDTO.getDeliver_order_start());
            Delivery delivery = new Delivery();
            delivery.setDeliverID(orderDTO.getRider_id());
            orders.setDelivery(delivery);

            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean deleteOrderss(OrdersDTO ordersDTO) throws Exception {
        return false;
    }


    @Override
    public OrdersDTO searchOrders(String id) throws Exception {
        Orders search = orderDAO.search(id);
        if (search != null) {
            return new OrdersDTO(search.getOrder_ID(), search.getOrder_date(), search.getOrder_time(), search.getStatus(), search.getPaymentStatus(), search.isHomeDelivery(), search.getTotal_price());
        }
        return null;
    }

    @Override
    public ArrayList<OrdersDTO> getAll() throws Exception {
        return null;
    }

    @Override
    public List<OrdersDTO> getAllOrdersByCustomerID(String id) throws Exception {
        return queryDAO.getOrdersByCustomerID(id);
    }

    @Override
    public List<OrdersDTO> getAllAvailableOrders() throws Exception {
        List<Orders> allAvailableOrders = orderDAO.getAllAvailableOrders();
        List<OrdersDTO> ordersDTOS = new ArrayList<>();
        for (Orders r : allAvailableOrders) {
            ordersDTOS.add(new OrdersDTO(r.getOrder_ID(), r.getOrder_date(), r.getOrder_time(), r.getStatus(), r.getPaymentStatus(), r.isHomeDelivery(), r.getTotal_price()));
        }
        return ordersDTOS;
    }

    @Override
    public List<OrdersDTO> getAllAvailableOrdersForDeliver() throws Exception {
        List<Orders> allAvailableOrders = orderDAO.getAllAvailableOrdersForDeliver();
        List<OrdersDTO> ordersDTOS = new ArrayList<>();
        for (Orders r : allAvailableOrders) {
            ordersDTOS.add(new OrdersDTO(r.getOrder_ID(), r.getOrder_date(), r.getOrder_time(), r.getStatus(), r.getPaymentStatus(), r.isHomeDelivery(), r.getTotal_price()));
        }
        return ordersDTOS;
    }
}

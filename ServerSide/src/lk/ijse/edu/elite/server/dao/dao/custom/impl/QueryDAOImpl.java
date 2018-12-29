package lk.ijse.edu.elite.server.dao.dao.custom.impl;

import lk.ijse.edu.elite.common.dto.OrdersDTO;
import lk.ijse.edu.elite.server.dao.CrudDAOImpl;
import lk.ijse.edu.elite.server.dao.CrudUtil;
import lk.ijse.edu.elite.server.dao.dao.custom.QueryDAO;
import lk.ijse.edu.elite.server.entity.Customer;
import lk.ijse.edu.elite.server.entity.Foods;
import lk.ijse.edu.elite.server.entity.OrderDetails;
import lk.ijse.edu.elite.server.entity.Orders;
import lk.ijse.edu.elite.server.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.sql.ResultSet;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    private SessionFactory sessionFactory;
    public QueryDAOImpl() {
        sessionFactory= HibernateUtil.getSessionFactory();
    }

    @Override
    public Customer getCustomerByOrderID(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select cust_id,addres,contact,cust_name,dob,nic from Customer c,Orders o where c.cust_id=o.customer_cust_id and o.order_ID=? ", id);
        if(rst.next()){
            return new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
        }
        return null;
//        Customer customer;
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            Query query = session.createQuery("cust_id,addres,contact,cust_name,dob,nic from Customer c,Orders o where c.cust_id=o.customer_cust_id and o.order_ID=:ID");
//            query.setString("ID",id);
//             customer = (Customer) query.uniqueResult();
//            session.getTransaction().commit();
//        }
//        return customer;
    }

    @Override
    public ArrayList<OrderDetails> getOrderDetailByID(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select foods_food_ID,food_name,category,description,unitPrice,orderd_Qty from OrderDetails od,Orders o,Foods f where o.order_ID=od.orders_order_ID and od.foods_food_ID=f.food_ID and o.order_ID=? ", id);
        ArrayList<OrderDetails>orderDetails=new ArrayList<>();
        while (rst.next()){
            orderDetails.add(new OrderDetails(new Foods(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(5)),new Orders(),rst.getDouble(5),rst.getInt(6)));
        }
        return orderDetails;
    }

    @Override
    public ArrayList<OrdersDTO> getOrdersByCustomerID(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from Orders where customer_cust_id=? ", id);
        ArrayList<OrdersDTO>ordersDTOS=new ArrayList<>();
        while (rst.next()){
            ordersDTOS.add(new OrdersDTO(rst.getString("order_ID"),rst.getDate("order_date"),rst.getTime("order_time"),rst.getString("status"),rst.getString("paymentStatus"),rst.getBoolean("homeDelivery"),rst.getDouble("total_price"),rst.getString("reception_reception_ID"),rst.getString("chef_chef_ID"),rst.getString("delivery_deliverID"),rst.getTime("chef_order_start"),rst.getTime("chef_order_end"),rst.getTime("deliver_order_start"),rst.getTime("deliver_order_end")));
        }
        return ordersDTOS;
    }
}

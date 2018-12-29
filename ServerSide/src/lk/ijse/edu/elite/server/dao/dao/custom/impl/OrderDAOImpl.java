package lk.ijse.edu.elite.server.dao.dao.custom.impl;

import lk.ijse.edu.elite.server.dao.CrudDAOImpl;
import lk.ijse.edu.elite.server.dao.CrudUtil;
import lk.ijse.edu.elite.server.dao.dao.custom.OrderDAO;
import lk.ijse.edu.elite.server.entity.*;
import lk.ijse.edu.elite.server.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private SessionFactory sessionFactory;

    public OrderDAOImpl() {
        sessionFactory= HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean save(Orders entity) throws Exception {
        return false;

    }

    @Override
    public boolean update(Orders entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Orders orders = session.get(Orders.class, entity.getOrder_ID());
            orders.setStatus(entity.getStatus());

            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean delete(Orders entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Orders orders = session.get(Orders.class, entity.getOrder_ID());
            session.remove(orders);

            session.getTransaction().commit();
        }
        return true;
    }



    @Override
    public Orders search(String s) throws Exception {
//        Orders orders;
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            orders = session.get(Orders.class, s);
//
//            System.out.println(orders);
//            session.getTransaction().commit();
//        }
//        return orders;
        ResultSet rst = CrudUtil.executeQuery("Select * from Orders where order_ID=? ", s);
        if (rst.next()){
            return new Orders(rst.getString("order_ID"),
                    rst.getDate("order_date"),
                    rst.getTime("order_time"),
                    rst.getDouble("total_price"),
                    rst.getString("status"),
                    rst.getString("paymentStatus"),
                    rst.getBoolean("homeDelivery"));
        }
        return null;
    }

    @Override
    public List<Orders> getAll() throws Exception {
        return null;
    }

    @Override
    public List<Orders> getAllAvailableOrders() throws Exception {
        List list;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Orders where status=:state");
            query.setString("state","Just placed");
            list = query.list();

            session.getTransaction().commit();
        }
        return list;
    }

    @Override
    public List<Orders> getAllAvailableOrdersForDeliver() throws Exception {
        List list;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Orders where status=:state and homeDelivery=:d_type");
            query.setString("state","Cooked ");
            query.setBoolean("d_type",true);
            list = query.list();

            session.getTransaction().commit();
        }
        return list;
    }
}


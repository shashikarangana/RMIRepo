package lk.ijse.edu.elite.server.dao.dao.custom.impl;

import lk.ijse.edu.elite.server.dao.CrudDAOImpl;
import lk.ijse.edu.elite.server.dao.dao.custom.CustomerDAO;
import lk.ijse.edu.elite.server.entity.Customer;
import lk.ijse.edu.elite.server.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDAOImpl  implements CustomerDAO {
    private SessionFactory sessionFactory;

    public CustomerDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean save(Customer entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Customer entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Customer customer = session.get(Customer.class, entity.getCust_id());

            customer.setAddres(entity.getAddres());
            customer.setContact(entity.getContact());
            customer.setCust_name(entity.getCust_name());
            customer.setDob(entity.getDob());
            customer.setNic(entity.getNic());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Customer entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Customer customer = session.get(Customer.class, entity.getCust_id());
            session.remove(customer);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public Customer search(String s) throws Exception {
        Customer customer;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Customer where nic=:cust_nic");
            query.setString("cust_nic",s);
            customer=(Customer)query.uniqueResult();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            return null;
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() throws Exception {
        List from_customer=null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql="from Customer";
            from_customer = session.createQuery(sql).list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            return null;
        }
        return from_customer;
    }
}

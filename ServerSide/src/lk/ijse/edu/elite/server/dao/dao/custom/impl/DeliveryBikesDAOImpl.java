package lk.ijse.edu.elite.server.dao.dao.custom.impl;

import lk.ijse.edu.elite.server.dao.CrudDAOImpl;
import lk.ijse.edu.elite.server.dao.dao.custom.DeliveryBikesDAO;
import lk.ijse.edu.elite.server.entity.Delivery;
import lk.ijse.edu.elite.server.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DeliveryBikesDAOImpl  implements DeliveryBikesDAO {
    private SessionFactory sessionFactory;

    public DeliveryBikesDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean save(Delivery entity) throws Exception {
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
    public boolean update(Delivery entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Delivery delivery = session.get(Delivery.class, entity.getDeliverID());

            delivery.setAddress(entity.getAddress());
            delivery.setBike_number(entity.getBike_number());
            delivery.setContact(entity.getContact());
            delivery.setD_Name(entity.getD_Name());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Delivery entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Delivery delivery = session.get(Delivery.class, entity.getDeliverID());
            session.remove(delivery);

            session.getTransaction().commit();
        }catch (HibernateException ex){
            return false;

        }
        return true;
    }

    @Override
    public Delivery search(String s) throws Exception {
        Delivery delivery;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            delivery = session.get(Delivery.class, s);


            session.getTransaction().commit();
        }catch (HibernateException e){
            return null;
        }
        return delivery;
    }

    @Override
    public List<Delivery> getAll() throws Exception {
        List from_delivery;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            String sql = "from Delivery";

            from_delivery = session.createQuery(sql).list();

            session.getTransaction().commit();

        }catch (HibernateException e){
            return null;
        }
        return from_delivery;
    }
}

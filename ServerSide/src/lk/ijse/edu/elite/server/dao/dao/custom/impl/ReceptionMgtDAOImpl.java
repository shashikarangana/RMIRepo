package lk.ijse.edu.elite.server.dao.dao.custom.impl;

import lk.ijse.edu.elite.server.dao.dao.custom.ReceptionMgtDAO;
import lk.ijse.edu.elite.server.entity.Reception;
import lk.ijse.edu.elite.server.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ReceptionMgtDAOImpl implements ReceptionMgtDAO {
    private SessionFactory sessionFactory;

    public ReceptionMgtDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean save(Reception entity) throws Exception {
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
    public boolean update(Reception entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Reception reception = session.get(Reception.class, entity.getReception_ID());
            reception.setReception_address(entity.getReception_address());
            reception.setReception_Contact(entity.getReception_Contact());
            reception.setReception_Name(entity.getReception_Name());
            reception.setReception_nic(entity.getReception_nic());

            session.getTransaction().commit();
        } catch (HibernateException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Reception entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Reception reception = session.get(Reception.class, entity.getReception_ID());
            session.remove(reception);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            return false;
        }
        return true;
    }


    @Override
    public Reception search(String s) throws Exception {
        Reception reception;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Reception where reception_nic=:nic");
            query.setString("nic", s);
            reception = (Reception) query.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            return null;
        }
        return reception;
    }

    @Override
    public List<Reception> getAll() throws Exception {
        List list = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            list = session.createQuery("from Reception").list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            return null;
        }
        return list;
    }
}

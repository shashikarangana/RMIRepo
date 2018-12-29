package lk.ijse.edu.elite.server.dao.dao.custom.impl;

import lk.ijse.edu.elite.server.dao.CrudDAOImpl;
import lk.ijse.edu.elite.server.dao.dao.custom.ChefDAO;
import lk.ijse.edu.elite.server.entity.Chef;
import lk.ijse.edu.elite.server.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ChefDAOImpl implements ChefDAO {
    private SessionFactory sessionFactory;
    public ChefDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean save(Chef entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(entity);

            session.getTransaction().commit();
        } catch (HibernateException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Chef entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Chef chef = session.get(Chef.class, entity.getChef_ID());

            chef.setChef_address(entity.getChef_address());
            chef.setChef_Contact(entity.getChef_Contact());
            chef.setChef_Name(entity.getChef_Name());
            chef.setNic(entity.getNic());

            session.getTransaction().commit();
        }catch (HibernateException e){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Chef entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Chef chef = session.get(Chef.class, entity.getChef_ID());

            session.remove(chef);

            session.getTransaction().commit();

        }catch (HibernateException e){
            return false;
        }
        return true;
    }


    @Override
    public Chef search(String s) throws Exception {
        Chef chef;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Chef where nic=:chef_nic");
            query.setString("chef_nic",s);
            chef=(Chef)query.uniqueResult();

            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
            return null;

        }
        return chef;

    }

    @Override
    public List<Chef> getAll() throws Exception {
        List list;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

             list=session.createQuery("from Chef").list();

            session.getTransaction().commit();
        }catch (HibernateException e){
            return null;
        }
        return list;
    }
}

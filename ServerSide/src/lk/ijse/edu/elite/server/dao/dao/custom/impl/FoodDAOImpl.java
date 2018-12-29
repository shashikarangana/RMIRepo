package lk.ijse.edu.elite.server.dao.dao.custom.impl;

import lk.ijse.edu.elite.server.dao.dao.custom.FoodDAO;
import lk.ijse.edu.elite.server.entity.Foods;
import lk.ijse.edu.elite.server.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class FoodDAOImpl implements FoodDAO {
    private SessionFactory sessionFactory;
    public FoodDAOImpl() {
        sessionFactory=HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean save(Foods entity) throws Exception {
        try {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                session.persist(entity);

                session.getTransaction().commit();
            }
        }catch (HibernateException ex){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Foods entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Foods foods = session.get(Foods.class, entity.getFood_ID());

            foods.setCategory(entity.getCategory());
            foods.setDescription(entity.getDescription());
            foods.setFood_name(entity.getFood_name());
            foods.setPrice(entity.getPrice());

            session.getTransaction().commit();

        }catch (HibernateException ex){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Foods entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Foods foods = session.get(Foods.class,entity.getFood_ID());
            session.remove(foods);
            session.getTransaction().commit();

        }catch (HibernateException ex){
            return false;
        }
        return true;
    }



    @Override
    public Foods search(String s) throws Exception {
        Foods foods=null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("from Foods where food_name=:food");
            query.setString("food",s);
            foods=(Foods)query.uniqueResult();
            session.getTransaction().commit();

        }catch (HibernateException ex){
            ex.printStackTrace();
            return null;

        }
        return foods;
    }

    @Override
    public List<Foods> getAll() throws Exception {
        List list;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            String sql="from Foods";
            list = session.createQuery(sql).list();

            session.getTransaction().commit();

        }catch (HibernateException ex){
            return null;
        }
        return list;
    }
}

package lk.ijse.edu.elite.server.dao;

import lk.ijse.edu.elite.common.db.DBConnection;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudDAOImpl <T, ID extends Serializable> implements CrudDAO<T, ID> {
    protected Session session;
    private Class<T> type;

    public CrudDAOImpl() {
        type = (Class<T>) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }



    @Override
    public boolean save(T entity) throws Exception {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(T entity) throws Exception {
        session.update(entity);
        return true;
    }

    @Override
    public boolean delete(T entity) throws Exception {
        session.remove(entity);
        return true;
    }

    @Override
    public T search(ID id) throws Exception {
        return session.get(type, id);
    }

    @Override
    public List<T> getAll() throws Exception {
        return session.createQuery("From "+type.getName()).list();
    }



}

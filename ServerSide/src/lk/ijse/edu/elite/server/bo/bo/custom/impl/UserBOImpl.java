package lk.ijse.edu.elite.server.bo.bo.custom.impl;

import lk.ijse.edu.elite.common.dto.UserDTO;
import lk.ijse.edu.elite.server.bo.bo.custom.UserBO;
import lk.ijse.edu.elite.server.entity.UserAccount;
import lk.ijse.edu.elite.server.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    private SessionFactory sessionFactory;
    public UserBOImpl() {
        sessionFactory= HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateUser(UserDTO userDto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return false;
    }

    @Override
    public UserDTO searchUser(String name) throws Exception {
        UserDTO userDTO;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query query = session.createQuery("From UserAccount where userName=:u_name");
            query.setString("u_name",name);
            UserAccount userAccount = (UserAccount) query.uniqueResult();
             userDTO = new UserDTO(userAccount.getId(), userAccount.getUserName(), userAccount.getPassword());
            session.getTransaction().commit();
        }
        return userDTO;
    }

    @Override
    public ArrayList<UserDTO> getAll() throws Exception {
        return null;
    }
}

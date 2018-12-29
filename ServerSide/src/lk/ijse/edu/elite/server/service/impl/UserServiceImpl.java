package lk.ijse.edu.elite.server.service.impl;

import lk.ijse.edu.elite.common.dto.UserDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.service.custom.UserService;
import lk.ijse.edu.elite.server.bo.BOFactory;
import lk.ijse.edu.elite.server.bo.bo.custom.UserBO;
import lk.ijse.edu.elite.server.bo.bo.custom.impl.UserBOImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    private UserBO userBO;

    public UserServiceImpl() throws RemoteException {
        userBO= new UserBOImpl();
    }

    @Override
    public boolean addUser(UserDTO userDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteUser(UserDTO userDTO) throws Exception {
        return false;
    }



    @Override
    public UserDTO searchUser(String name) throws Exception {
        return userBO.searchUser(name);
    }

    @Override
    public ArrayList<UserDTO> getAll() throws Exception {
        return null;
    }

//    @Override
//    public void register(Observer observer) throws Exception {
//
//    }
//
//    @Override
//    public void unregister(Observer observer) throws Exception {
//
//    }
//
//    @Override
//    public void notifyAllObservers(String message) throws Exception {
//
//    }
//
//    @Override
//    public boolean reserved(Object id) throws Exception {
//        return false;
//    }
//
//    @Override
//    public boolean released(Object id) throws Exception {
//        return false;
//    }
}

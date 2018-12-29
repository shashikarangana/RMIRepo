package lk.ijse.edu.elite.server.service.impl;

import lk.ijse.edu.elite.common.dto.ReceptionDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.service.custom.ReceptionMgtService;
import lk.ijse.edu.elite.server.bo.BOFactory;
import lk.ijse.edu.elite.server.bo.bo.custom.ReceptionMgtBO;
import lk.ijse.edu.elite.server.bo.bo.custom.impl.ReceptionMgtBOImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ReceptionMServiceImpl extends UnicastRemoteObject implements ReceptionMgtService {
    private ReceptionMgtBO receptionMgtBO;

    public ReceptionMServiceImpl() throws RemoteException {
        receptionMgtBO = new ReceptionMgtBOImpl();
    }

    @Override
    public boolean addReception(ReceptionDTO receptionDTO) throws Exception {
        return receptionMgtBO.saveReception(receptionDTO);
    }

    @Override
    public boolean updateReception(ReceptionDTO receptionDTO) throws Exception {
        return receptionMgtBO.updateReception(receptionDTO);
    }

    @Override
    public boolean deleteReception(ReceptionDTO receptionDTO) throws Exception {
        return false;
    }



    @Override
    public ReceptionDTO searchReception(String id) throws Exception {
        return receptionMgtBO.searchReception(id);
    }

    @Override
    public ArrayList<ReceptionDTO> getAll() throws Exception {
        return receptionMgtBO.getAll();
    }

    @Override
    public void register(Observer observer) throws Exception {

    }

    @Override
    public void unregister(Observer observer) throws Exception {

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {

    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return false;
    }

    @Override
    public boolean released(Object id) throws Exception {
        return false;
    }
}

package lk.ijse.edu.elite.server.service.impl;

import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.service.custom.IDGeneratinService;
import lk.ijse.edu.elite.server.dao.DAOFactory;
import lk.ijse.edu.elite.server.dao.dao.custom.IdControllerDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.NumberFormat;

public class IDGeneratinServiceImpl extends UnicastRemoteObject implements IDGeneratinService {
    private IdControllerDAO idControllerDAO;

    public IDGeneratinServiceImpl() throws RemoteException {
        idControllerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ID);
    }

    public String getNewID(String tableName, String colName, String prifix) throws Exception {
        String lastId = idControllerDAO.getLastID(tableName, colName);
        if (lastId != null) {
            int id = Integer.parseInt(lastId.split(prifix)[1]);
            id++;
            NumberFormat numberFormat = NumberFormat.getIntegerInstance();
            numberFormat.setMinimumIntegerDigits(3);
            numberFormat.setGroupingUsed(false);
            String newID = numberFormat.format(id);
            return prifix + newID;
        } else {
            return prifix + "001";
        }
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

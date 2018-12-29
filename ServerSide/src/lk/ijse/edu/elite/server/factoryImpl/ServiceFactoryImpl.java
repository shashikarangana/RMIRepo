package lk.ijse.edu.elite.server.factoryImpl;

import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.server.service.impl.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {
    private static ServiceFactory serviceFactory;

    public ServiceFactoryImpl() throws RemoteException {
    }

    public static ServiceFactory getInstance() throws RemoteException{
        if(serviceFactory==null){
            serviceFactory=new ServiceFactoryImpl();
        }
        return serviceFactory;
    }


    @Override
    public <T> T getSuperService(ServiceTypes types) throws Exception {
        switch (types){
            case CHEF:
                return (T)new ChefMgtServiceImpl();
            case REPORT:
                return (T) new ReportServiceImpl();
            case ID:
                return (T)new IDGeneratinServiceImpl();
            case ORDERS:
                return (T) new ReceptionOrderServiceImpl();
            case RECEPTION:
                return (T) new ReceptionMServiceImpl();
            case CUSTOMER:
                return (T) new CustomerMServiceImpl();
            case DELIVERY:
                return (T) new DeliveryBikeBikeServiceImpl();
            case FOOD:
                return (T)new FoodServiceImpl();
            case USER:
                return (T) new UserServiceImpl();
                default:
                    return null;
        }
    }
}

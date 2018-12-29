package lk.ijse.edu.elite.common.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {
    public enum ServiceTypes{
        CHEF,CUSTOMER,DELIVERY,FOOD,ID,RECEPTION,ORDERS,REPORT,USER;
    }
    public <T>T getSuperService(ServiceTypes types)throws Exception;
}

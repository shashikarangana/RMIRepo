package lk.ijse.edu.elite.client.proxy;


import lk.ijse.edu.elite.common.service.ServiceFactory;
import lk.ijse.edu.elite.common.service.service.custom.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ProxyHandler implements ServiceFactory {
    private static ProxyHandler proxyHandler;
    private ChefService chefService;
    private CustomerMService customerMService;
    private DeliveryBikeService deliveryBikeService;
    private FoodService foodService;
    private IDGeneratinService idGeneratinService;
    private ReceptionMgtService receptionMgtService;
    private ReceptionOrderService receptionOrderService;
    private ReportService reportService;
    private UserService userService;

    public ProxyHandler() {
        try {
            ServiceFactory serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:3030/Elite");
            chefService = serviceFactory.getSuperService(ServiceTypes.CHEF);
            customerMService = serviceFactory.getSuperService(ServiceTypes.CUSTOMER);
            deliveryBikeService = serviceFactory.getSuperService(ServiceTypes.DELIVERY);
            foodService = serviceFactory.getSuperService(ServiceTypes.FOOD);
            idGeneratinService = serviceFactory.getSuperService(ServiceTypes.ID);
            receptionMgtService = serviceFactory.getSuperService(ServiceTypes.RECEPTION);
            receptionOrderService = serviceFactory.getSuperService(ServiceTypes.ORDERS);
            reportService = serviceFactory.getSuperService(ServiceTypes.REPORT);
            userService = serviceFactory.getSuperService(ServiceTypes.USER);

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ProxyHandler getInstance() {
        if (proxyHandler == null) {
            proxyHandler = new ProxyHandler();
        }
        return proxyHandler;
    }


    @Override
    public <T> T getSuperService(ServiceTypes types) throws Exception {
        switch (types) {
            case CHEF:
                return (T) chefService;
            case FOOD:
                return (T) foodService;
            case DELIVERY:
                return (T) deliveryBikeService;
            case RECEPTION:
                return (T) receptionMgtService;
            case ORDERS:
                return (T) receptionOrderService;
            case ID:
                return (T) idGeneratinService;
            case CUSTOMER:
                return (T) customerMService;
            case REPORT:
                return (T) reportService;
            case USER:
                return (T) userService;
                default:
                    return null;
        }
    }
}




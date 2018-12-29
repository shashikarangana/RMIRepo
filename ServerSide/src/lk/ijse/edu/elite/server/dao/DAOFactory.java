package lk.ijse.edu.elite.server.dao;

import lk.ijse.edu.elite.server.dao.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public enum DAOTypes {
        CHEF, CUSTOMER, DELIVERY, FOOD, ORDERS, ID, RECEPTION, QUERY;
    }

    public DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public <T> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CHEF:
                return (T) new ChefDAOImpl();
            case FOOD:
                return (T) new FoodDAOImpl();
            case ORDERS:
                return (T) new OrderDAOImpl();
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case DELIVERY:
                return (T) new DeliveryBikesDAOImpl();
            case RECEPTION:
                return (T) new ReceptionMgtDAOImpl();
            case ID:
                return (T) new IdControllerDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}

package lk.ijse.edu.elite.server.bo;

import lk.ijse.edu.elite.server.bo.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    public enum BOTypes {
        CHEF, CUSTOMER, DELIVERY, FOOD, ORDERS, ORDERDETAIL, RECEPTION,USER;
    }

    public BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CHEF:
                return (T) new ChefBOImpl();
            case FOOD:
                return (T) new FoodBOImpl();
            case ORDERS:
                return (T) new OrderBOImpl();
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case DELIVERY:
                return (T) new DeliveryBikesBOImpl();
            case RECEPTION:
                return (T) new ReceptionMgtBOImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailBOImpl();
            case USER:
                return (T)new UserBOImpl();
            default:
                return null;
        }
    }
}

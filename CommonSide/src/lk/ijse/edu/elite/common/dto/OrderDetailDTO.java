package lk.ijse.edu.elite.common.dto;

import java.io.Serializable;

public class OrderDetailDTO implements Serializable {
    private String food_code;
    private String order_id;
    private double unitPrice;
    private int orderdQty;

    public OrderDetailDTO(String food_code, String order_id, double unitPrice, int orderdQty) {
        this.food_code = food_code;
        this.order_id = order_id;
        this.unitPrice = unitPrice;
        this.orderdQty = orderdQty;
    }

    public OrderDetailDTO() {
    }

    public String getFood_code() {
        return food_code;
    }

    public void setFood_code(String food_code) {
        this.food_code = food_code;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderdQty() {
        return orderdQty;
    }

    public void setOrderdQty(int orderdQty) {
        this.orderdQty = orderdQty;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "food_code='" + food_code + '\'' +
                ", order_id='" + order_id + '\'' +
                ", unitPrice=" + unitPrice +
                ", orderdQty=" + orderdQty +
                '}';
    }
}

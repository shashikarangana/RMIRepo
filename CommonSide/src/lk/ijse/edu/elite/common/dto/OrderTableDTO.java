package lk.ijse.edu.elite.common.dto;

import java.io.Serializable;

public class OrderTableDTO implements Serializable {
    private String foodCode;
    private String foodName;
    private double unitPrice;
    private int qty;
    private double total;

    public OrderTableDTO(String foodCode, String foodName, double unitPrice, int qty, double total) {
        this.foodCode = foodCode;
        this.foodName = foodName;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
    }

    public OrderTableDTO() {
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderTableDTO{" +
                "foodCode='" + foodCode + '\'' +
                ", foodName='" + foodName + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}

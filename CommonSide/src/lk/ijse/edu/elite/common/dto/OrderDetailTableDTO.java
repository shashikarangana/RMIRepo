package lk.ijse.edu.elite.common.dto;

import java.io.Serializable;

public class OrderDetailTableDTO implements Serializable {
    private String food_id;
    private String food_name;
    private String category;
    private String description;
    private double unitPrice;
    private int orderdQty;
    private double total;

    public OrderDetailTableDTO(String food_id, String food_name, String category, String description, double unitPrice, int orderdQty) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.category = category;
        this.description = description;
        this.unitPrice = unitPrice;
        this.orderdQty = orderdQty;
    }

    public OrderDetailTableDTO(String food_id, String food_name, String category, String description, double unitPrice, int orderdQty, double total) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.category = category;
        this.description = description;
        this.unitPrice = unitPrice;
        this.orderdQty = orderdQty;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderDetailTableDTO() {
    }

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "OrderDetailTableDTO{" +
                "food_id='" + food_id + '\'' +
                ", food_name='" + food_name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", orderdQty=" + orderdQty +
                '}';
    }
}

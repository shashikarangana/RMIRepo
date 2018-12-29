package lk.ijse.edu.elite.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class OrderDetails implements Serializable {
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
    @Id
    private Foods foods;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
    @Id
    private Orders orders;
    private double unitPrice;
    private int orderd_Qty;

    public OrderDetails(Foods foods, Orders orders, double unitPrice, int orderd_Qty) {
        this.foods = foods;
        this.orders = orders;
        this.unitPrice = unitPrice;
        this.orderd_Qty = orderd_Qty;
    }

    public OrderDetails(double unitPrice, int orderd_Qty) {
        this.unitPrice = unitPrice;
        this.orderd_Qty = orderd_Qty;
    }

    public OrderDetails() {
    }

    public Foods getFoods() {
        return foods;
    }

    public void setFoods(Foods foods) {
        this.foods = foods;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderd_Qty() {
        return orderd_Qty;
    }

    public void setOrderd_Qty(int orderd_Qty) {
        this.orderd_Qty = orderd_Qty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "foods=" + foods +
                ", orders=" + orders +
                ", unitPrice=" + unitPrice +
                ", orderd_Qty=" + orderd_Qty +
                '}';
    }
}

package lk.ijse.edu.elite.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Foods {
    @Id
    private String food_ID;
    private String food_name;
    private String category;
    private String description;
    private  double price;
    @OneToMany(mappedBy = "foods",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<OrderDetails> orderDetails=new ArrayList<>();

    public Foods(String food_ID, String food_name, String category, String description, double price, List<OrderDetails> orderDetails) {
        this.food_ID = food_ID;
        this.food_name = food_name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.orderDetails = orderDetails;
    }

    public Foods(String food_ID, String food_name, String category, String description, double price) {
        this.food_ID = food_ID;
        this.food_name = food_name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public Foods() {
    }

    public Foods(String food_ID) {
        this.food_ID = food_ID;
    }

    public String getFood_ID() {
        return food_ID;
    }

    public void setFood_ID(String food_ID) {
        this.food_ID = food_ID;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
//
    @Override
    public String toString() {
        return "Foods{" +
                "food_ID='" + food_ID + '\'' +
                ", food_name='" + food_name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

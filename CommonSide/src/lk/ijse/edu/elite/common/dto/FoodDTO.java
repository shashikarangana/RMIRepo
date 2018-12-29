package lk.ijse.edu.elite.common.dto;

import java.io.Serializable;

public class FoodDTO implements Serializable {
    private String food_ID;
    private String food_name;
    private String category;
    private String description;
    private  double price;

    public FoodDTO(String food_ID, String food_name, String category, String description, double price) {
        this.food_ID = food_ID;
        this.food_name = food_name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public FoodDTO() {
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

    @Override
    public String toString() {
        return "FoodDTO{" +
                "food_ID='" + food_ID + '\'' +
                ", food_name='" + food_name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}

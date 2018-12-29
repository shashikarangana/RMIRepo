package lk.ijse.edu.elite.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Delivery {
    @Id
    private String deliverID;
    private String d_Name;
    private String bike_number;
    private String contact;
    private String address;
    @OneToMany(mappedBy = "delivery",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Orders>orders=new ArrayList<>();

    public Delivery(String deliverID, String d_Name, String bike_number, String contact, String address, List<Orders> orders) {
        this.deliverID = deliverID;
        this.d_Name = d_Name;
        this.bike_number = bike_number;
        this.contact = contact;
        this.address = address;
        this.orders = orders;
    }

    public Delivery(String deliverID, String d_Name, String bike_number, String contact, String address) {
        this.deliverID = deliverID;
        this.d_Name = d_Name;
        this.bike_number = bike_number;
        this.contact = contact;
        this.address = address;
    }

    public Delivery() {
    }

    public String getDeliverID() {
        return deliverID;
    }

    public void setDeliverID(String deliverID) {
        this.deliverID = deliverID;
    }

    public String getD_Name() {
        return d_Name;
    }

    public void setD_Name(String d_Name) {
        this.d_Name = d_Name;
    }

    public String getBike_number() {
        return bike_number;
    }

    public void setBike_number(String bike_number) {
        this.bike_number = bike_number;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliverID='" + deliverID + '\'' +
                ", d_Name='" + d_Name + '\'' +
                ", bike_number='" + bike_number + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}

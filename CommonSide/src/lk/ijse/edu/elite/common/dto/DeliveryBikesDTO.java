package lk.ijse.edu.elite.common.dto;

import java.io.Serializable;

public class DeliveryBikesDTO implements Serializable {
    private String deliverID;
    private String d_Name;
    private String bike_number;
    private String contact;
    private String address;

    public DeliveryBikesDTO(String deliverID, String d_Name, String bike_number, String contact, String address) {
        this.deliverID = deliverID;
        this.d_Name = d_Name;
        this.bike_number = bike_number;
        this.contact = contact;
        this.address = address;
    }

    public DeliveryBikesDTO() {
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

    @Override
    public String toString() {
        return "DeliveryBikesDTO{" +
                "deliverID='" + deliverID + '\'' +
                ", d_Name='" + d_Name + '\'' +
                ", bike_number='" + bike_number + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

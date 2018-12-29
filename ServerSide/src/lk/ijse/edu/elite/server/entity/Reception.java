package lk.ijse.edu.elite.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Reception {
    @Id
    private String reception_ID;
    private String reception_Name;
    private String reception_Contact;
    private String reception_address;
    private String reception_nic;
    @OneToMany(mappedBy = "reception",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Orders> orders=new ArrayList<>();

    public Reception(String reception_ID, String reception_Name, String reception_Contact, String reception_address, String reception_nic, List<Orders> orders) {
        this.reception_ID = reception_ID;
        this.reception_Name = reception_Name;
        this.reception_Contact = reception_Contact;
        this.reception_address = reception_address;
        this.reception_nic = reception_nic;
        this.orders = orders;
    }

    public Reception(String reception_ID, String reception_Name, String reception_Contact, String reception_address, String reception_nic) {
        this.reception_ID = reception_ID;
        this.reception_Name = reception_Name;
        this.reception_Contact = reception_Contact;
        this.reception_address = reception_address;
        this.reception_nic = reception_nic;
    }

    public Reception() {
    }

    public String getReception_ID() {
        return reception_ID;
    }

    public void setReception_ID(String reception_ID) {
        this.reception_ID = reception_ID;
    }

    public String getReception_Name() {
        return reception_Name;
    }

    public void setReception_Name(String reception_Name) {
        this.reception_Name = reception_Name;
    }

    public String getReception_Contact() {
        return reception_Contact;
    }

    public void setReception_Contact(String reception_Contact) {
        this.reception_Contact = reception_Contact;
    }

    public String getReception_address() {
        return reception_address;
    }

    public void setReception_address(String reception_address) {
        this.reception_address = reception_address;
    }

    public String getReception_nic() {
        return reception_nic;
    }

    public void setReception_nic(String reception_nic) {
        this.reception_nic = reception_nic;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Reception{" +
                "reception_ID='" + reception_ID + '\'' +
                ", reception_Name='" + reception_Name + '\'' +
                ", reception_Contact='" + reception_Contact + '\'' +
                ", reception_address='" + reception_address + '\'' +
                ", reception_nic='" + reception_nic + '\'' +
                ", orders=" + orders +
                '}';
    }
}

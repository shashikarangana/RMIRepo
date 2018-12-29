package lk.ijse.edu.elite.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Chef {
    @Id
    private String chef_ID;
    private String chef_Name;
    private String chef_Contact;
    private String chef_address;
    private String nic;
    @OneToMany(mappedBy = "chef",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Orders>orders=new ArrayList<>();

    public Chef(String chef_ID, String chef_Name, String chef_Contact, String chef_address, String nic, List<Orders> orders) {
        this.chef_ID = chef_ID;
        this.chef_Name = chef_Name;
        this.chef_Contact = chef_Contact;
        this.chef_address = chef_address;
        this.nic = nic;
        this.orders = orders;
    }

    public Chef(String chef_ID, String chef_Name, String chef_Contact, String chef_address, String nic) {
        this.chef_ID = chef_ID;
        this.chef_Name = chef_Name;
        this.chef_Contact = chef_Contact;
        this.chef_address = chef_address;
        this.nic = nic;
    }

    public Chef() {
    }

    public String getChef_ID() {
        return chef_ID;
    }

    public void setChef_ID(String chef_ID) {
        this.chef_ID = chef_ID;
    }

    public String getChef_Name() {
        return chef_Name;
    }

    public void setChef_Name(String chef_Name) {
        this.chef_Name = chef_Name;
    }

    public String getChef_Contact() {
        return chef_Contact;
    }

    public void setChef_Contact(String chef_Contact) {
        this.chef_Contact = chef_Contact;
    }

    public String getChef_address() {
        return chef_address;
    }

    public void setChef_address(String chef_address) {
        this.chef_address = chef_address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Chef{" +
                "chef_ID='" + chef_ID + '\'' +
                ", chef_Name='" + chef_Name + '\'' +
                ", chef_Contact='" + chef_Contact + '\'' +
                ", chef_address='" + chef_address + '\'' +
                ", nic='" + nic + '\'' +
                ", orders=" + orders +
                '}';
    }
}

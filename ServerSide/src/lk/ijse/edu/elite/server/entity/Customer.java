package lk.ijse.edu.elite.server.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    private String cust_id;
    private String cust_name;
    private String addres;
    private String contact;
    private String nic;
    private String dob;
    @OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
    private List<Orders>orders=new ArrayList<>();

    public Customer(String cust_id, String cust_name, String addres, String contact, String nic, String dob, List<Orders> orders) {
        this.cust_id = cust_id;
        this.cust_name = cust_name;
        this.addres = addres;
        this.contact = contact;
        this.nic = nic;
        this.dob = dob;
        this.orders = orders;
    }

    public Customer(String cust_id, String cust_name, String addres, String contact, String nic, String dob) {
        this.cust_id = cust_id;
        this.cust_name = cust_name;
        this.addres = addres;
        this.contact = contact;
        this.nic = nic;
        this.dob = dob;
    }

    public Customer() {
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cust_id='" + cust_id + '\'' +
                ", cust_name='" + cust_name + '\'' +
                ", addres='" + addres + '\'' +
                ", contact='" + contact + '\'' +
                ", nic='" + nic + '\'' +
                ", dob='" + dob + '\'' +
                ", orders=" + orders +
                '}';
    }
}

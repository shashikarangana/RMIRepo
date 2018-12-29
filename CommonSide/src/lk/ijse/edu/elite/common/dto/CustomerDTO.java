package lk.ijse.edu.elite.common.dto;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private String cust_id;
    private String cust_name;
    private String addres;
    private String contact;
    private String nic;
    private String dob;

    public CustomerDTO(String cust_id, String cust_name, String addres, String contact, String nic, String dob) {
        this.cust_id = cust_id;
        this.cust_name = cust_name;
        this.addres = addres;
        this.contact = contact;
        this.nic = nic;
        this.dob = dob;
    }

    public CustomerDTO() {
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "cust_id='" + cust_id + '\'' +
                ", cust_name='" + cust_name + '\'' +
                ", addres='" + addres + '\'' +
                ", contact='" + contact + '\'' +
                ", nic='" + nic + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}

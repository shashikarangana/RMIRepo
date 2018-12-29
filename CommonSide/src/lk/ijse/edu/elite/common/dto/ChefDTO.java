package lk.ijse.edu.elite.common.dto;

import java.io.Serializable;

public class ChefDTO implements Serializable {
    private String chef_ID;
    private String chef_Name;
    private String chef_Contact;
    private String chef_address;
    private String nic;

    public ChefDTO(String chef_id, String chef_name, String chef_contact, String chef_address, String nic) {
        chef_ID = chef_id;
        chef_Name = chef_name;
        chef_Contact = chef_contact;
        this.chef_address = chef_address;
        this.nic = nic;
    }

    public ChefDTO() {
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

    @Override
    public String toString() {
        return "ChefDTO{" +
                "chef_ID='" + chef_ID + '\'' +
                ", chef_Name='" + chef_Name + '\'' +
                ", chef_Contact='" + chef_Contact + '\'' +
                ", chef_address='" + chef_address + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}

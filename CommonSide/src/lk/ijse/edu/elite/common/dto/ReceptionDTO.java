package lk.ijse.edu.elite.common.dto;

import java.io.Serializable;

public class ReceptionDTO implements Serializable {
    private String reception_ID;
    private String reception_Name;
    private String reception_Contact;
    private String reception_address;
    private String reception_nic;

    public ReceptionDTO(String reception_id, String reception_name, String reception_contact, String reception_address, String reception_nic) {
        reception_ID = reception_id;
        reception_Name = reception_name;
        reception_Contact = reception_contact;
        this.reception_address = reception_address;
        this.reception_nic = reception_nic;
    }

    public ReceptionDTO() {
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

    @Override
    public String toString() {
        return "ReceptionDTO{" +
                "reception_ID='" + reception_ID + '\'' +
                ", reception_Name='" + reception_Name + '\'' +
                ", reception_Contact='" + reception_Contact + '\'' +
                ", reception_address='" + reception_address + '\'' +
                ", reception_nic='" + reception_nic + '\'' +
                '}';
    }
}

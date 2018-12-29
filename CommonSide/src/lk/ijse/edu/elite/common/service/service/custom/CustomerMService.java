package lk.ijse.edu.elite.common.service.service.custom;

import lk.ijse.edu.elite.common.dto.CustomerDTO;
import lk.ijse.edu.elite.common.observer.Subject;
import lk.ijse.edu.elite.common.reservation.Reservation;
import lk.ijse.edu.elite.common.service.SuperService;

import java.util.ArrayList;

public interface CustomerMService extends SuperService,Reservation,Subject {
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception;

    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception;

    public boolean deleteCustomer(CustomerDTO customerDTO)throws Exception;

    public CustomerDTO searchCustomer(String id)throws Exception;

    public CustomerDTO searchCustomerByOrderID(String id)throws Exception;

    public ArrayList<CustomerDTO> getAll()throws Exception;
}

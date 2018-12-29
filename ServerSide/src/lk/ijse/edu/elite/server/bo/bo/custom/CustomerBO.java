package lk.ijse.edu.elite.server.bo.bo.custom;

import lk.ijse.edu.elite.common.dto.CustomerDTO;
import lk.ijse.edu.elite.server.bo.SuperBO;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public boolean saveCustomer(CustomerDTO customerDTO) throws Exception;

    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception;

    public boolean deleteCustomer(CustomerDTO customerDTO)throws Exception;

    public CustomerDTO searchCustomer(String id)throws Exception;

    public CustomerDTO searchCustomerByOrderID(String id)throws Exception;

    public ArrayList<CustomerDTO> getAll()throws Exception;
}

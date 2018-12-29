package lk.ijse.edu.elite.server.bo.bo.custom.impl;

import lk.ijse.edu.elite.common.dto.CustomerDTO;
import lk.ijse.edu.elite.server.bo.bo.custom.CustomerBO;
import lk.ijse.edu.elite.server.dao.DAOFactory;
import lk.ijse.edu.elite.server.dao.dao.custom.CustomerDAO;
import lk.ijse.edu.elite.server.dao.dao.custom.QueryDAO;
import lk.ijse.edu.elite.server.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO;
    private QueryDAO queryDAO;
    public CustomerBOImpl() {
        customerDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        queryDAO=DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.save(new Customer(customerDTO.getCust_id(),customerDTO.getCust_name(),customerDTO.getAddres(),customerDTO.getContact(),customerDTO.getNic(),customerDTO.getDob()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.update(new Customer(customerDTO.getCust_id(),customerDTO.getCust_name(),customerDTO.getAddres(),customerDTO.getContact(),customerDTO.getNic(),customerDTO.getDob()));
    }

    @Override
    public boolean deleteCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.delete(new Customer(customerDTO.getCust_id(),customerDTO.getCust_name(),customerDTO.getAddres(),customerDTO.getContact(),customerDTO.getNic(),customerDTO.getDob()));
    }



    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Customer search = customerDAO.search(id);
        if(search!=null){
            return new CustomerDTO(search.getCust_id(),search.getCust_name(),search.getAddres(),search.getContact(),search.getNic(),search.getDob());
        }
        return null;
    }

    @Override
    public CustomerDTO searchCustomerByOrderID(String id) throws Exception {
        Customer c = queryDAO.getCustomerByOrderID(id);
        if(c!=null){
            return new CustomerDTO(c.getCust_id(),c.getCust_name(),c.getAddres(),c.getContact(),c.getNic(),c.getDob());
        }
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws Exception {
        List<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO>customerDTOS=new ArrayList<>();
        for (Customer c:all){
            customerDTOS.add(new CustomerDTO(c.getCust_id(),c.getCust_name(),c.getAddres(),c.getContact(),c.getNic(),c.getDob()));
        }
        return customerDTOS;
    }
}

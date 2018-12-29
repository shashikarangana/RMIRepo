package lk.ijse.edu.elite.server.service.impl;

import lk.ijse.edu.elite.common.dto.CustomerDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.service.custom.CustomerMService;
import lk.ijse.edu.elite.server.bo.BOFactory;
import lk.ijse.edu.elite.server.bo.bo.custom.CustomerBO;
import lk.ijse.edu.elite.server.bo.bo.custom.impl.CustomerBOImpl;
import lk.ijse.edu.elite.server.reservation.impl.ReservationImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CustomerMServiceImpl extends UnicastRemoteObject implements CustomerMService {
    private CustomerBO customerBO;
    private static ArrayList <Observer> allCustomerObservers=new ArrayList<>();
    private static ReservationImpl<CustomerMService> customerServiceReservation = new ReservationImpl<>();
    public CustomerMServiceImpl() throws RemoteException {
        customerBO= new CustomerBOImpl();
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        boolean b = customerBO.saveCustomer(customerDTO);
        if (b) {
            notifyAllObservers("New Customer is added to the System under " + customerDTO.getCust_id());
        }
        return b;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        if(customerServiceReservation.reserve(customerDTO.getCust_id(),this,true)){
            boolean b = customerBO.updateCustomer(customerDTO);
            if(b){
                if(customerServiceReservation.checkState(customerDTO.getCust_id(),this)){
                    notifyAllObservers("Existing Customer "+customerDTO.getCust_id()+" is Updated..!");
                    customerServiceReservation.release(customerDTO.getCust_id(),this);
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(CustomerDTO customerDTO) throws Exception {
        if (customerServiceReservation.reserve(customerDTO.getCust_id(),this,true)){
            boolean b = customerBO.deleteCustomer(customerDTO);
            if (b){
                if (customerServiceReservation.checkState(customerDTO.getCust_id(),this)){
                    notifyAllObservers("Customer "+customerDTO.getCust_id()+" is Deleted from System..!");
                    customerServiceReservation.release(customerDTO.getCust_id(),this);
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        return customerBO.searchCustomer(id);
    }

    @Override
    public CustomerDTO searchCustomerByOrderID(String id) throws Exception {
        return customerBO.searchCustomerByOrderID(id);
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws Exception {
        return customerBO.getAll();
    }

    @Override
    public void register(Observer observer) throws Exception {
        allCustomerObservers.add(observer);

    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allCustomerObservers.remove(observer);

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for(Observer allObserver:allCustomerObservers){
            new Thread(()->{
                try {
                    allObserver.update(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return customerServiceReservation.reserve(id,this,true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return customerServiceReservation.release(id,this);
    }
}

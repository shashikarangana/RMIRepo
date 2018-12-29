package lk.ijse.edu.elite.server.service.impl;

import lk.ijse.edu.elite.common.dto.DeliveryBikesDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.service.custom.DeliveryBikeService;
import lk.ijse.edu.elite.server.bo.BOFactory;
import lk.ijse.edu.elite.server.bo.bo.custom.DeliveryBikesBO;
import lk.ijse.edu.elite.server.bo.bo.custom.impl.DeliveryBikesBOImpl;
import lk.ijse.edu.elite.server.reservation.impl.ReservationImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DeliveryBikeBikeServiceImpl extends UnicastRemoteObject implements DeliveryBikeService {
    private DeliveryBikesBO deliveryBikesBO;
    private static ArrayList <Observer> allDeliveryBickObservers=new ArrayList<>();
    private static ReservationImpl<DeliveryBikeService> deliveryBickServiceReservation = new ReservationImpl<>();

    public DeliveryBikeBikeServiceImpl() throws RemoteException {
        deliveryBikesBO=new  DeliveryBikesBOImpl();
    }

    @Override
    public boolean addDelivery(DeliveryBikesDTO deliveryBikesDTO) throws Exception {
        boolean b = deliveryBikesBO.saveDelivery(deliveryBikesDTO);
        if (b) {
            notifyAllObservers("New Delivery Bick is added to the System under " + deliveryBikesDTO.getDeliverID());
        }
        return b;
    }

    @Override
    public boolean updateDelivery(DeliveryBikesDTO deliveryBikesDTO) throws Exception {
        if(deliveryBickServiceReservation.reserve(deliveryBikesDTO.getDeliverID(),this,true)){
            boolean b = deliveryBikesBO.updateDelivery(deliveryBikesDTO);
            if(b){
                if(deliveryBickServiceReservation.checkState(deliveryBikesDTO.getDeliverID(),this)){
                    notifyAllObservers("Existing Deliver Bick "+deliveryBikesDTO.getDeliverID()+" is Updated..!");
                    deliveryBickServiceReservation.release(deliveryBikesDTO.getDeliverID(),this);
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public boolean deleteDelivery(DeliveryBikesDTO deliveryBikesDTO) throws Exception {
        if (deliveryBickServiceReservation.reserve(deliveryBikesDTO.getDeliverID(),this,true)){
            boolean b = deliveryBikesBO.deleteDelivery(deliveryBikesDTO);
            if (b){
                if (deliveryBickServiceReservation.checkState(deliveryBikesDTO.getDeliverID(),this)){
                    notifyAllObservers("Deliver Bick "+deliveryBikesDTO.getDeliverID()+" is Deleted from System..!");
                    deliveryBickServiceReservation.release(deliveryBikesDTO.getDeliverID(),this);
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public DeliveryBikesDTO searchDelivery(String id) throws Exception {
        return deliveryBikesBO.searchDelivery(id);
    }

    @Override
    public ArrayList<DeliveryBikesDTO> getAll() throws Exception {
        return deliveryBikesBO.getAll();
    }

    @Override
    public void register(Observer observer) throws Exception {
        allDeliveryBickObservers.add(observer);


    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allDeliveryBickObservers.remove(observer);

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {

        for(Observer allObserver:allDeliveryBickObservers){
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
        return deliveryBickServiceReservation.reserve(id,this,true);

    }

    @Override
    public boolean released(Object id) throws Exception {
        return deliveryBickServiceReservation.release(id,this);
    }
}

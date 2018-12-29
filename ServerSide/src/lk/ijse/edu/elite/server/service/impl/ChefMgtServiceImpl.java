package lk.ijse.edu.elite.server.service.impl;

import lk.ijse.edu.elite.common.dto.ChefDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.service.custom.ChefService;
import lk.ijse.edu.elite.server.bo.BOFactory;
import lk.ijse.edu.elite.server.bo.bo.custom.ChefBO;
import lk.ijse.edu.elite.server.bo.bo.custom.impl.ChefBOImpl;
import lk.ijse.edu.elite.server.reservation.impl.ReservationImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChefMgtServiceImpl extends UnicastRemoteObject implements ChefService {
    private ChefBO chefBO;
    private static ArrayList <Observer> allChefObservers=new ArrayList<>();
    private static ReservationImpl<ChefService> chefServiceReservation = new ReservationImpl<>();

    public ChefMgtServiceImpl() throws RemoteException {
        chefBO=new ChefBOImpl();
    }

    @Override
    public boolean addChef(ChefDTO chefDTO) throws Exception {

        boolean b = chefBO.saveChef(chefDTO);
        if (b) {
            notifyAllObservers("New Chef is added to the System under " + chefDTO.getChef_ID());
        }
        return b;
//        return chefBO.saveChef(chefDTO);
    }

    @Override
    public boolean updateChef(ChefDTO chefDTO) throws Exception {
        if(chefServiceReservation.reserve(chefDTO.getChef_ID(),this,true)){
            boolean b = chefBO.updateChef(chefDTO);
            if(b){
                if(chefServiceReservation.checkState(chefDTO.getChef_ID(),this)){
                    notifyAllObservers("Existing Chef "+chefDTO.getChef_ID()+" is Updated..!");
                    chefServiceReservation.release(chefDTO.getChef_ID(),this);
                    return true;
                }

            }
        }
        return false;
//        return chefBO.updateChef(chefDTO);
    }

    @Override
    public boolean deleteChef(ChefDTO chefDTO) throws Exception {
        if (chefServiceReservation.reserve(chefDTO.getChef_ID(),this,true)){
            boolean b = chefBO.deleteChef(chefDTO);
            if (b){
                if (chefServiceReservation.checkState(chefDTO.getChef_ID(),this)){
                    notifyAllObservers("Chef "+chefDTO.getChef_ID()+" is Deleted from System..!");
                    chefServiceReservation.release(chefDTO.getChef_ID(),this);
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public ChefDTO searchChef(String id) throws Exception {
        return chefBO.searchChef(id);
    }

    @Override
    public ArrayList<ChefDTO> getAll() throws Exception {
        return chefBO.getAll();

    }

    @Override
    public void register(Observer observer) throws Exception {
        allChefObservers.add(observer);

    }

    @Override
    public void unregister(Observer observer) throws Exception {
        allChefObservers.remove(observer);

    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for(Observer allObserver:allChefObservers){
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
        return chefServiceReservation.reserve(id,this,true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return chefServiceReservation.release(id,this);
    }
}

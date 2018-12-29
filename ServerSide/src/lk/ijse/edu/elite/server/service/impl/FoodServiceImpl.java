package lk.ijse.edu.elite.server.service.impl;

import lk.ijse.edu.elite.common.dto.FoodDTO;
import lk.ijse.edu.elite.common.observer.Observer;
import lk.ijse.edu.elite.common.service.service.custom.FoodService;
import lk.ijse.edu.elite.server.bo.BOFactory;
import lk.ijse.edu.elite.server.bo.bo.custom.FoodBO;
import lk.ijse.edu.elite.server.bo.bo.custom.impl.FoodBOImpl;
import lk.ijse.edu.elite.server.reservation.impl.ReservationImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class FoodServiceImpl extends UnicastRemoteObject implements FoodService {
    private FoodBO foodBO;

    private static ArrayList <Observer> allFoodObservers=new ArrayList<>();
    private static ReservationImpl<FoodService> foodServiceReservation = new ReservationImpl<>();

    public FoodServiceImpl() throws RemoteException {
        foodBO=new FoodBOImpl();
    }

    @Override
    public boolean addFood(FoodDTO foodDTO) throws Exception {
        boolean b = foodBO.saveFood(foodDTO);
        if (b) {
            notifyAllObservers("New Food Item is added to the System under " + foodDTO.getFood_ID());
        }
        return b;
    }

    @Override
    public boolean updateFood(FoodDTO foodDTO) throws Exception {
        if(foodServiceReservation.reserve(foodDTO.getFood_ID(),this,true)){
            boolean b = foodBO.updateFood(foodDTO);
            if(b){
                if(foodServiceReservation.checkState(foodDTO.getFood_ID(),this)){
                    notifyAllObservers("Existing Food "+foodDTO.getFood_ID()+" is Updated..!");
                    foodServiceReservation.release(foodDTO.getFood_ID(),this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteFood(FoodDTO foodDTO) throws Exception {
        if (foodServiceReservation.reserve(foodDTO.getFood_ID(),this,true)){
            boolean b = foodBO.deleteFood(foodDTO);
            if (b){
                if (foodServiceReservation.checkState(foodDTO.getFood_ID(),this)){
                    notifyAllObservers("Food "+foodDTO.getFood_ID()+" is Deleted from System..!");
                    foodServiceReservation.release(foodDTO.getFood_ID(),this);
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    public FoodDTO searchFood(String id) throws Exception {
        return foodBO.searchFood(id);
    }

    @Override
    public ArrayList<FoodDTO> getAll() throws Exception {
        return foodBO.getAll();
    }

    @Override
    public void register(Observer observer) throws Exception {
        allFoodObservers.add(observer);

    }

    @Override
    public void unregister(Observer observer) throws Exception {

        allFoodObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers(String message) throws Exception {

        for(Observer allObserver:allFoodObservers){
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
        return foodServiceReservation.reserve(id,this,true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return foodServiceReservation.release(id,this);
    }
}

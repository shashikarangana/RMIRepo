package lk.ijse.edu.elite.common.service.service.custom;

import lk.ijse.edu.elite.common.dto.FoodDTO;
import lk.ijse.edu.elite.common.observer.Subject;
import lk.ijse.edu.elite.common.reservation.Reservation;
import lk.ijse.edu.elite.common.service.SuperService;

import java.util.ArrayList;

public interface FoodService extends SuperService ,Reservation,Subject {
    public boolean addFood(FoodDTO foodDTO) throws Exception;

    public boolean updateFood(FoodDTO foodDTO) throws Exception;

    public boolean deleteFood(FoodDTO foodDTO)throws Exception;

    public FoodDTO searchFood(String id)throws Exception;

    public ArrayList<FoodDTO> getAll()throws Exception;
}

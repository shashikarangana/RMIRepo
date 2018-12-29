package lk.ijse.edu.elite.common.service.service.custom;

import lk.ijse.edu.elite.common.dto.ChefDTO;
import lk.ijse.edu.elite.common.observer.Subject;
import lk.ijse.edu.elite.common.reservation.Reservation;
import lk.ijse.edu.elite.common.service.SuperService;

import java.util.ArrayList;

public interface ChefService extends SuperService,Reservation,Subject {
    public boolean addChef(ChefDTO chefDTO) throws Exception;

    public boolean updateChef(ChefDTO chefDTO) throws Exception;

    public boolean deleteChef(ChefDTO chefDTO)throws Exception;

    public ChefDTO searchChef(String id)throws Exception;

    public ArrayList<ChefDTO> getAll()throws Exception;
}

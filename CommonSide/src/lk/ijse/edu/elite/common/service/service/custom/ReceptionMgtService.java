package lk.ijse.edu.elite.common.service.service.custom;

import lk.ijse.edu.elite.common.dto.ReceptionDTO;
import lk.ijse.edu.elite.common.observer.Subject;
import lk.ijse.edu.elite.common.reservation.Reservation;
import lk.ijse.edu.elite.common.service.SuperService;

import java.util.ArrayList;

public interface ReceptionMgtService extends SuperService ,Reservation,Subject {
    public boolean addReception(ReceptionDTO receptionDTO) throws Exception;

    public boolean updateReception(ReceptionDTO receptionDTO) throws Exception;

    public boolean deleteReception(ReceptionDTO receptionDTO)throws Exception;

    public ReceptionDTO searchReception(String id)throws Exception;

    public ArrayList<ReceptionDTO> getAll()throws Exception;
}

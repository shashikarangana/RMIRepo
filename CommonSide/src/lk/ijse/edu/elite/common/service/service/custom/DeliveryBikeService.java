package lk.ijse.edu.elite.common.service.service.custom;

import lk.ijse.edu.elite.common.dto.DeliveryBikesDTO;
import lk.ijse.edu.elite.common.observer.Subject;
import lk.ijse.edu.elite.common.reservation.Reservation;
import lk.ijse.edu.elite.common.service.SuperService;

import java.util.ArrayList;

public interface DeliveryBikeService extends SuperService,Reservation,Subject {
    public boolean addDelivery(DeliveryBikesDTO deliveryBikesDTO) throws Exception;

    public boolean updateDelivery(DeliveryBikesDTO deliveryBikesDTO) throws Exception;

    public boolean deleteDelivery(DeliveryBikesDTO deliveryBikesDTO)throws Exception;

    public DeliveryBikesDTO searchDelivery(String id)throws Exception;

    public ArrayList<DeliveryBikesDTO> getAll()throws Exception;
}

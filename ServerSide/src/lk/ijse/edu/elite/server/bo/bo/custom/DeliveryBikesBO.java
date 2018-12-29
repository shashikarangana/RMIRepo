package lk.ijse.edu.elite.server.bo.bo.custom;

import lk.ijse.edu.elite.common.dto.DeliveryBikesDTO;
import lk.ijse.edu.elite.server.bo.SuperBO;

import java.util.ArrayList;

public interface DeliveryBikesBO extends SuperBO {

    public boolean saveDelivery(DeliveryBikesDTO deliveryBikesDTO) throws Exception;

    public boolean updateDelivery(DeliveryBikesDTO deliveryBikesDTO) throws Exception;

    public boolean deleteDelivery(DeliveryBikesDTO deliveryBikesDTO)throws Exception;

    public DeliveryBikesDTO searchDelivery(String id)throws Exception;

    public ArrayList<DeliveryBikesDTO> getAll()throws Exception;
}

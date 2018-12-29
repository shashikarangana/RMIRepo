package lk.ijse.edu.elite.server.bo.bo.custom.impl;

import lk.ijse.edu.elite.common.dto.DeliveryBikesDTO;
import lk.ijse.edu.elite.server.bo.bo.custom.DeliveryBikesBO;
import lk.ijse.edu.elite.server.dao.CrudDAO;
import lk.ijse.edu.elite.server.dao.DAOFactory;
import lk.ijse.edu.elite.server.dao.dao.custom.DeliveryBikesDAO;
import lk.ijse.edu.elite.server.entity.Delivery;

import java.util.ArrayList;
import java.util.List;

public class DeliveryBikesBOImpl  implements DeliveryBikesBO {
    private DeliveryBikesDAO deliveryBikesDAO;
    public DeliveryBikesBOImpl() {
        deliveryBikesDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DELIVERY);
    }

    @Override
    public boolean saveDelivery(DeliveryBikesDTO deliveryBikesDTO) throws Exception {
        return deliveryBikesDAO.save(new Delivery(deliveryBikesDTO.getDeliverID(),deliveryBikesDTO.getD_Name(),deliveryBikesDTO.getBike_number(),deliveryBikesDTO.getContact(),deliveryBikesDTO.getAddress()));
    }

    @Override
    public boolean updateDelivery(DeliveryBikesDTO deliveryBikesDTO) throws Exception {
        return deliveryBikesDAO.update(new Delivery(deliveryBikesDTO.getDeliverID(),deliveryBikesDTO.getD_Name(),deliveryBikesDTO.getBike_number(),deliveryBikesDTO.getContact(),deliveryBikesDTO.getAddress()));
    }

    @Override
    public boolean deleteDelivery(DeliveryBikesDTO deliveryBikesDTO) throws Exception {
        return deliveryBikesDAO.delete(new Delivery(deliveryBikesDTO.getDeliverID(),deliveryBikesDTO.getD_Name(),deliveryBikesDTO.getBike_number(),deliveryBikesDTO.getContact(),deliveryBikesDTO.getAddress()));
    }

    @Override
    public DeliveryBikesDTO searchDelivery(String id) throws Exception {
        Delivery search = deliveryBikesDAO.search(id);
        if(search!=null){
            return new DeliveryBikesDTO(search.getDeliverID(),search.getD_Name(),search.getBike_number(),search.getContact(),search.getAddress());
        }
        return null;
    }




    @Override
    public ArrayList<DeliveryBikesDTO> getAll() throws Exception {
        List<Delivery> all = deliveryBikesDAO.getAll();
        ArrayList<DeliveryBikesDTO>dtos=new ArrayList<>();
        for(Delivery d:all){
            dtos.add(new DeliveryBikesDTO(d.getDeliverID(),d.getD_Name(),d.getBike_number(),d.getContact(),d.getAddress()));
        }
        return dtos;
    }
}

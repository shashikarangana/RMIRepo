package lk.ijse.edu.elite.server.bo.bo.custom;

import lk.ijse.edu.elite.common.dto.OrderDetailDTO;
import lk.ijse.edu.elite.common.dto.OrderDetailTableDTO;
import lk.ijse.edu.elite.server.bo.SuperBO;

import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    public boolean saveOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    public boolean updateOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    public boolean deleteOrderDetail(OrderDetailDTO orderDetailDTO)throws Exception;

    public ArrayList<OrderDetailTableDTO> searchOrderDetail(String id)throws Exception;

    public ArrayList<OrderDetailDTO> getAll()throws Exception;
}

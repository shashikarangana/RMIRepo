package lk.ijse.edu.elite.server.bo.bo.custom.impl;

import lk.ijse.edu.elite.common.dto.OrderDetailDTO;
import lk.ijse.edu.elite.common.dto.OrderDetailTableDTO;
import lk.ijse.edu.elite.server.bo.bo.custom.OrderDetailBO;
import lk.ijse.edu.elite.server.dao.DAOFactory;
import lk.ijse.edu.elite.server.dao.dao.custom.QueryDAO;
import lk.ijse.edu.elite.server.entity.Foods;
import lk.ijse.edu.elite.server.entity.OrderDetails;
import lk.ijse.edu.elite.server.entity.Orders;

import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {
    private QueryDAO queryDAO;
    public OrderDetailBOImpl() {
        queryDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    @Override
    public boolean saveOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        return false;
    }



    @Override
    public ArrayList<OrderDetailTableDTO> searchOrderDetail(String id) throws Exception {
        ArrayList<OrderDetails> orderDetailByID = queryDAO.getOrderDetailByID(id);
        ArrayList<OrderDetailTableDTO>orderDetailDTOS=new ArrayList<>();
        for (OrderDetails o:orderDetailByID){
            Foods foods = o.getFoods();
            Orders orders = o.getOrders();
            orderDetailDTOS.add(new OrderDetailTableDTO(foods.getFood_ID(),foods.getFood_name(),foods.getCategory(),foods.getDescription(),foods.getPrice(),o.getOrderd_Qty()));
        }
        return orderDetailDTOS;
    }

    @Override
    public ArrayList<OrderDetailDTO> getAll() throws Exception {
        return null;
    }
}

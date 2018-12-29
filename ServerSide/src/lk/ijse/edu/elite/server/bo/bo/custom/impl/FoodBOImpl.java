package lk.ijse.edu.elite.server.bo.bo.custom.impl;

import lk.ijse.edu.elite.common.dto.FoodDTO;
import lk.ijse.edu.elite.server.bo.bo.custom.FoodBO;
import lk.ijse.edu.elite.server.dao.DAOFactory;
import lk.ijse.edu.elite.server.dao.dao.custom.FoodDAO;
import lk.ijse.edu.elite.server.entity.Foods;

import java.util.ArrayList;
import java.util.List;

public class FoodBOImpl implements FoodBO {
    private FoodDAO foodDAO;
    public FoodBOImpl() {
        foodDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.FOOD);
    }

    @Override
    public boolean saveFood(FoodDTO foodDTO) throws Exception {
        return foodDAO.save(new Foods(foodDTO.getFood_ID(),foodDTO.getFood_name(),foodDTO.getCategory(),foodDTO.getDescription(),foodDTO.getPrice()));
    }

    @Override
    public boolean updateFood(FoodDTO foodDTO) throws Exception {
        return foodDAO.update(new Foods(foodDTO.getFood_ID(),foodDTO.getFood_name(),foodDTO.getCategory(),foodDTO.getDescription(),foodDTO.getPrice()));
    }

    @Override
    public boolean deleteFood(FoodDTO foodDTO) throws Exception {
        return foodDAO.delete(new Foods(foodDTO.getFood_ID(),foodDTO.getFood_name(),foodDTO.getCategory(),foodDTO.getDescription(),foodDTO.getPrice()));

    }



    @Override
    public FoodDTO searchFood(String id) throws Exception {
        Foods search = foodDAO.search(id);
        if(search!=null){
            return new FoodDTO(search.getFood_ID(),search.getFood_name(),search.getCategory(),search.getDescription(),search.getPrice());
        }
        return null;
    }

    @Override
    public ArrayList<FoodDTO> getAll() throws Exception {
        List<Foods> all = foodDAO.getAll();
        ArrayList<FoodDTO>dtos=new ArrayList<>();
        for(Foods f:all){
            dtos.add(new FoodDTO(f.getFood_ID(),f.getFood_name(),f.getCategory(),f.getDescription(),f.getPrice()));
        }
     return dtos;
    }
}

package lk.ijse.edu.elite.server.bo.bo.custom;

import lk.ijse.edu.elite.common.dto.FoodDTO;
import lk.ijse.edu.elite.server.bo.SuperBO;

import java.util.ArrayList;

public interface FoodBO extends SuperBO {
    public boolean saveFood(FoodDTO foodDTO) throws Exception;

    public boolean updateFood(FoodDTO foodDTO) throws Exception;

    public boolean deleteFood(FoodDTO foodDTO)throws Exception;

    public FoodDTO searchFood(String id)throws Exception;

    public ArrayList<FoodDTO> getAll()throws Exception;
}

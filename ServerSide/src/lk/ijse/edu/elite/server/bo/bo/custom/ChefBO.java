package lk.ijse.edu.elite.server.bo.bo.custom;

import lk.ijse.edu.elite.common.dto.ChefDTO;
import lk.ijse.edu.elite.server.bo.SuperBO;

import java.util.ArrayList;

public interface ChefBO extends SuperBO {
    public boolean saveChef(ChefDTO chefDTO) throws Exception;

    public boolean updateChef(ChefDTO chefDTO) throws Exception;

    public boolean deleteChef(ChefDTO chefDTO)throws Exception;

    public ChefDTO searchChef(String id)throws Exception;

    public ArrayList<ChefDTO> getAll()throws Exception;
}

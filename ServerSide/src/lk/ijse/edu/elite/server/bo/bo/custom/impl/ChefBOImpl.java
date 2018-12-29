package lk.ijse.edu.elite.server.bo.bo.custom.impl;

import lk.ijse.edu.elite.common.dto.ChefDTO;
import lk.ijse.edu.elite.server.bo.bo.custom.ChefBO;
import lk.ijse.edu.elite.server.dao.DAOFactory;
import lk.ijse.edu.elite.server.dao.dao.custom.ChefDAO;
import lk.ijse.edu.elite.server.entity.Chef;

import java.util.ArrayList;
import java.util.List;

public class ChefBOImpl implements ChefBO {
    private ChefDAO chefDAO;
    public ChefBOImpl() {
        chefDAO= DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CHEF);
    }

    @Override
    public boolean saveChef(ChefDTO chefDTO) throws Exception {
        return chefDAO.save(new Chef(chefDTO.getChef_ID(),chefDTO.getChef_Name(),chefDTO.getChef_Contact(),chefDTO.getChef_address(),chefDTO.getNic()));
    }

    @Override
    public boolean updateChef(ChefDTO chefDTO) throws Exception {
        return chefDAO.update(new Chef(chefDTO.getChef_ID(),chefDTO.getChef_Name(),chefDTO.getChef_Contact(),chefDTO.getChef_address(),chefDTO.getNic()));
    }

    @Override
    public boolean deleteChef(ChefDTO chefDTO) throws Exception {
        return chefDAO.delete(new Chef(chefDTO.getChef_ID(),chefDTO.getChef_Name(),chefDTO.getChef_Contact(),chefDTO.getChef_address(),chefDTO.getNic()));
    }



    @Override
    public ChefDTO searchChef(String id) throws Exception {
        Chef search = chefDAO.search(id);
        if(search!=null){
            return new ChefDTO(search.getChef_ID(),search.getChef_Name(),search.getChef_Contact(),search.getChef_address(),search.getNic());
        }
        return null;
    }

    @Override
    public ArrayList<ChefDTO> getAll() throws Exception {
        List<Chef> all = chefDAO.getAll();
        ArrayList<ChefDTO>chefDTOS=new ArrayList<>();
        for (Chef c:all){
            chefDTOS.add(new ChefDTO(c.getChef_ID(),c.getChef_Name(),c.getChef_Contact(),c.getChef_address(),c.getNic()));
        }
        return chefDTOS;
    }
}

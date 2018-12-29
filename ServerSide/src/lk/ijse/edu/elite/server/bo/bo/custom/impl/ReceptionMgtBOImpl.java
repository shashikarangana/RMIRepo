package lk.ijse.edu.elite.server.bo.bo.custom.impl;

import lk.ijse.edu.elite.common.dto.ReceptionDTO;
import lk.ijse.edu.elite.server.bo.bo.custom.ReceptionMgtBO;
import lk.ijse.edu.elite.server.dao.DAOFactory;
import lk.ijse.edu.elite.server.dao.dao.custom.ReceptionMgtDAO;
import lk.ijse.edu.elite.server.entity.Reception;

import java.util.ArrayList;
import java.util.List;

public class ReceptionMgtBOImpl implements ReceptionMgtBO {
    private ReceptionMgtDAO receptionMgtDAO;

    public ReceptionMgtBOImpl() {
        receptionMgtDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RECEPTION);
    }

    @Override
    public boolean saveReception(ReceptionDTO receptionDTO) throws Exception {
        return receptionMgtDAO.save(new Reception(receptionDTO.getReception_ID(), receptionDTO.getReception_Name(), receptionDTO.getReception_Contact(), receptionDTO.getReception_address(), receptionDTO.getReception_nic()));
    }

    @Override
    public boolean updateReception(ReceptionDTO receptionDTO) throws Exception {
        return receptionMgtDAO.update(new Reception(receptionDTO.getReception_ID(), receptionDTO.getReception_Name(), receptionDTO.getReception_Contact(), receptionDTO.getReception_address(), receptionDTO.getReception_nic()));
    }

    @Override
    public boolean deleteReception(ReceptionDTO receptionDTO) throws Exception {
        return receptionMgtDAO.delete(new Reception(receptionDTO.getReception_ID(), receptionDTO.getReception_Name(), receptionDTO.getReception_Contact(), receptionDTO.getReception_address(), receptionDTO.getReception_nic()));

    }


    @Override
    public ReceptionDTO searchReception(String id) throws Exception {
        Reception search = receptionMgtDAO.search(id);
        if (search != null) {
            return new ReceptionDTO(search.getReception_ID(), search.getReception_Name(), search.getReception_Contact(), search.getReception_address(), search.getReception_nic());
        }
        return null;
    }

    @Override
    public ArrayList<ReceptionDTO> getAll() throws Exception {
        List<Reception> all = receptionMgtDAO.getAll();
        ArrayList<ReceptionDTO> dtos = new ArrayList<>();
        for (Reception r : all) {
            dtos.add(new ReceptionDTO(r.getReception_ID(), r.getReception_Name(), r.getReception_Contact(), r.getReception_address(), r.getReception_nic()));
        }
        return dtos;
    }
}

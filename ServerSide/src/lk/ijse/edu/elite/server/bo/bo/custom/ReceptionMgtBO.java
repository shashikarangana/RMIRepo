package lk.ijse.edu.elite.server.bo.bo.custom;

import lk.ijse.edu.elite.common.dto.ReceptionDTO;
import lk.ijse.edu.elite.server.bo.SuperBO;

import java.util.ArrayList;

public interface ReceptionMgtBO extends SuperBO {
    public boolean saveReception(ReceptionDTO receptionDTO) throws Exception;

    public boolean updateReception(ReceptionDTO receptionDTO) throws Exception;

    public boolean deleteReception(ReceptionDTO receptionDTO)throws Exception;

    public ReceptionDTO searchReception(String id)throws Exception;

    public ArrayList<ReceptionDTO> getAll()throws Exception;
}

package lk.ijse.edu.elite.server.bo.bo.custom;

import lk.ijse.edu.elite.common.dto.UserDTO;
import lk.ijse.edu.elite.server.bo.SuperBO;

import java.util.ArrayList;

public interface UserBO extends SuperBO {
    public boolean saveUser(UserDTO userDTO) throws Exception;

    public boolean updateUser(UserDTO userDto) throws Exception;

    public boolean deleteUser(String id)throws Exception;

    public UserDTO searchUser(String name)throws Exception;

    public ArrayList<UserDTO> getAll()throws Exception;
}

package lk.ijse.edu.elite.common.service.service.custom;

import lk.ijse.edu.elite.common.dto.UserDTO;
import lk.ijse.edu.elite.common.observer.Subject;
import lk.ijse.edu.elite.common.reservation.Reservation;
import lk.ijse.edu.elite.common.service.SuperService;

import java.util.ArrayList;

public interface UserService extends SuperService  {
    public boolean addUser(UserDTO userDTO) throws Exception;

    public boolean updateUser(UserDTO userDTO) throws Exception;

    public boolean deleteUser(UserDTO userDTO)throws Exception;

    public UserDTO searchUser(String name)throws Exception;

    public ArrayList<UserDTO> getAll()throws Exception;
}

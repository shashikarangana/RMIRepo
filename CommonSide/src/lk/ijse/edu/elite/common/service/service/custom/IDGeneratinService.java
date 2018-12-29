package lk.ijse.edu.elite.common.service.service.custom;

import lk.ijse.edu.elite.common.observer.Subject;
import lk.ijse.edu.elite.common.reservation.Reservation;
import lk.ijse.edu.elite.common.service.SuperService;

public interface IDGeneratinService extends SuperService ,Reservation,Subject {
    public String getNewID(String tableName, String colName, String prifix) throws Exception ;
}

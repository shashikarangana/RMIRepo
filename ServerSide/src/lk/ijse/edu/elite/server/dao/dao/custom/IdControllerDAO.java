package lk.ijse.edu.elite.server.dao.dao.custom;

import lk.ijse.edu.elite.server.dao.SuperDAO;

public interface IdControllerDAO extends SuperDAO {
    public String getLastID(String tableName, String colName) throws Exception ;
}

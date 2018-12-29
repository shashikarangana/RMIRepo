package lk.ijse.edu.elite.server.dao.dao.custom.impl;

import lk.ijse.edu.elite.server.dao.dao.custom.IdControllerDAO;
import lk.ijse.edu.elite.common.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class IdControllerDAOImpl implements IdControllerDAO {
    @Override
    public  String getLastID(String tableName, String colName) throws Exception {
        String sql = "select " + colName + " from " + tableName + " order by 1 desc limit 1";
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}

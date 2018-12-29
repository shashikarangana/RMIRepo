package lk.ijse.edu.elite.common.db;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements Serializable {
    private static DBConnection dbConnection;

    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
//        File file = new File("/lk/ijse/edu/elite/common/db/application.properties");
//        FileReader fileReader = new FileReader(file);
//        Properties properties = new Properties();
//        properties.load(fileReader);
//
//        String ip = properties.getProperty("ip");
//        String port = properties.getProperty("port");
//        String db = properties.getProperty("db");
//        String user = properties.getProperty("user");
//        String password = properties.getProperty("password");
//        String jdbc = "jdbc:mysql://" + ip + ":" + port + "/" + db;
        String jdbc = "jdbc:mysql://localhost:3306/DB";
        connection = DriverManager.getConnection(jdbc, "root", "1234");
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException, IOException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
}

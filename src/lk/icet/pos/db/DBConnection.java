package lk.icet.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;

    private static DBConnection dbConnection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_system_1",
                "root","1234");
    }
    public static synchronized DBConnection getInstance() throws SQLException, ClassNotFoundException {
       return dbConnection==null?dbConnection = new DBConnection():dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}

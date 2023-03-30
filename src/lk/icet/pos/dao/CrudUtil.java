package lk.icet.pos.dao;

import lk.icet.pos.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String sql, Object...params) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject((i+1),params[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T) preparedStatement.executeQuery();
        }
        return (T) (Boolean)(preparedStatement.executeUpdate()>0);
    }
}

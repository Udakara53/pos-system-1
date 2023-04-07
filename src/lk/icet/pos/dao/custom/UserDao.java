package lk.icet.pos.dao.custom;

import lk.icet.pos.dao.CrudDao;
import lk.icet.pos.entity.User;
import java.sql.SQLException;

public interface UserDao extends CrudDao<User,String> {
    public void initializeUsers() throws SQLException, ClassNotFoundException;
}

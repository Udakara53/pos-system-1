package lk.icet.pos.bo.custom;

import lk.icet.pos.bo.SuperBo;
import lk.icet.pos.dto.UserDto;
import java.sql.SQLException;

public interface UserBo extends SuperBo {
    public void initializeUsers() throws SQLException, ClassNotFoundException;

    public UserDto findUser(String username) throws Exception;
}

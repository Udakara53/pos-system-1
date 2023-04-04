package lk.icet.pos.bo.custom.impl;

import lk.icet.pos.bo.custom.UserBo;
import lk.icet.pos.dao.custom.UserDao;
import lk.icet.pos.dao.custom.impl.UserDaoImpl;
import lk.icet.pos.dto.UserDto;
import lk.icet.pos.entity.User;

import java.sql.SQLException;

public class UserBoImpl implements UserBo {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public void initializeUsers() throws SQLException, ClassNotFoundException {
        userDao.initializeUsers();
    }

    @Override
    public UserDto findUser(String username) throws Exception {
        User u = userDao.find(username);
        return new UserDto(u.getUsername(),u.getPassword());
    }
}

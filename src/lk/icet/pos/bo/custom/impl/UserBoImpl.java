package lk.icet.pos.bo.custom.impl;

import lk.icet.pos.bo.custom.UserBo;
import lk.icet.pos.dao.DaoFactory;
import lk.icet.pos.dao.custom.UserDao;
import lk.icet.pos.dto.UserDto;
import lk.icet.pos.entity.User;
import lk.icet.pos.enums.DaoType;
import java.sql.SQLException;

public class UserBoImpl implements UserBo {

    private UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);
    @Override
    public void initializeUsers() throws SQLException, ClassNotFoundException {
        userDao.initializeUsers();
    }

    @Override
    public UserDto findUser(String username) throws Exception {
        User u = userDao.find(username);
        return u!=null? new UserDto(u.getUsername(),u.getPassword()):null;
    }
}

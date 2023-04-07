package lk.icet.pos.dao.custom;

import lk.icet.pos.dao.CrudDao;
import lk.icet.pos.entity.Order;
import java.sql.SQLException;

public interface OrderDao extends CrudDao<Order,String> {
    public String generateOrderId() throws SQLException, ClassNotFoundException;
}

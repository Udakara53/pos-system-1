package lk.icet.pos.dao.custom;

import lk.icet.pos.dao.CrudDao;
import lk.icet.pos.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends CrudDao<Item,String> {
    public List<String> loadItemCodes() throws SQLException, ClassNotFoundException;

    public boolean updateQty(String code,int qty) throws SQLException, ClassNotFoundException;
}

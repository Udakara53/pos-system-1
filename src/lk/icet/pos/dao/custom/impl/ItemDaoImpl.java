package lk.icet.pos.dao.custom.impl;

import lk.icet.pos.dao.custom.ItemDao;
import lk.icet.pos.entity.Item;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item item) throws Exception {
        return false;
    }
    @Override
    public boolean update(Item item) throws Exception {
        return false;
    }

    @Override
    public Item find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public List<Item> findAll() throws Exception {
        return null;
    }
}

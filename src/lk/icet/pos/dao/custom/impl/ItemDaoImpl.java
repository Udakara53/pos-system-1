package lk.icet.pos.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.icet.pos.dao.CrudUtil;
import lk.icet.pos.dao.custom.ItemDao;
import lk.icet.pos.entity.Item;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item i) throws Exception {
        return CrudUtil.execute("INSERT INTO item VALUES(?,?,?,?)", i.getCode(), i.getDescription(), i.getQtyOnHand(), i.getUnitPrice());
    }
    @Override
    public boolean update(Item i) throws Exception {
        return CrudUtil.execute("UPDATE item SET description=?,qty_on_hand=?,unit_price=? WHERE code=?",i.getDescription(),i.getQtyOnHand(),i.getUnitPrice(),i.getCode());
    }

    @Override
    public Item find(String code) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT*FROM item WHERE code=?", code);
        if (rst.next()){
            return new Item(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
        }else{
            new Alert(Alert.AlertType.WARNING,"Item code is not found!").show();
            return null;
        }
    }

    @Override
    public boolean delete(String i) throws Exception {
        return CrudUtil.execute("DELETE FROM item WHERE code=?",i);
    }

    @Override
    public List<Item> findAll() throws Exception {
        ArrayList<Item> itemList = new ArrayList<>();
        ResultSet resultItem = CrudUtil.execute("SELECT*FROM item");
        while(resultItem.next()){
            itemList.add(new Item(
                    resultItem.getString(1),
                    resultItem.getString(2),
                    resultItem.getInt(3),
                    resultItem.getDouble(4)
            ));
        }return itemList;
    }
}

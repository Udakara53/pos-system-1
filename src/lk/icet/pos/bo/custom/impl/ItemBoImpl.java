package lk.icet.pos.bo.custom.impl;

import lk.icet.pos.bo.custom.ItemBo;
import lk.icet.pos.dao.custom.ItemDao;
import lk.icet.pos.dao.custom.impl.ItemDaoImpl;
import lk.icet.pos.dto.CustomerDto;
import lk.icet.pos.dto.ItemDto;
import lk.icet.pos.entity.Customer;
import lk.icet.pos.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {

    private ItemDao itemDao = new ItemDaoImpl();

    @Override
    public boolean saveItem(ItemDto dto) throws Exception {
        return itemDao.save(new Item(dto.getCode(),dto.getDescription(), dto.getQtyOnHand(), dto.getUnitPrice()));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws Exception {
        return itemDao.update(new Item(dto.getCode(),dto.getDescription(), dto.getQtyOnHand(), dto.getUnitPrice()));
    }

    @Override
    public ItemDto findItem(String id) throws Exception {
        Item i = itemDao.find(id);
        return  i!=null ?new ItemDto(i.getCode(),i.getDescription(),i.getQtyOnHand(),i.getUnitPrice()):null;
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDao.delete(id);
    }

    @Override
    public List<ItemDto> findAllItems(String id) throws Exception {
        List<Item> itemList =  itemDao.findAll();
        List<ItemDto> itemDtoList =new ArrayList<>();
        for (Item c:itemList
        ) {
            itemDtoList.add(new ItemDto(c.getCode(),c.getDescription(),c.getQtyOnHand(),c.getUnitPrice()));
        }
        return itemDtoList;
    }
}

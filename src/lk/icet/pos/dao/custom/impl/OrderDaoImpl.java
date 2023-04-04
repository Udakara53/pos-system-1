package lk.icet.pos.dao.custom.impl;

import lk.icet.pos.dao.CrudUtil;
import lk.icet.pos.dao.custom.OrderDao;
import lk.icet.pos.entity.Order;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(Order order) throws Exception {
        return CrudUtil.execute(
                "INSERT INTO orders VALUES(?,?,?,?)",
                order.getOrderId(),order.getCustomer(),order.getDate(),order.getTotal()
        );
    }

    @Override
    public boolean update(Order order) throws Exception {
        return false;
    }

    @Override
    public Order find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public List<Order> findAll() throws Exception {
        return null;
    }
}

package lk.icet.pos.dao.custom.impl;

import lk.icet.pos.dao.CrudDao;
import lk.icet.pos.dao.CrudUtil;
import lk.icet.pos.dao.custom.OrderDetailsDao;
import lk.icet.pos.entity.OrderDetails;

import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean save(OrderDetails orderDetails) throws Exception {
        return CrudUtil.execute(
                "INSERT INTO order_details VALUES(?,?,?,?)",
                orderDetails.getCode(),
                orderDetails.getOrderId(),
                orderDetails.getQty(),
                orderDetails.getUnitPrice()
        );
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws Exception {
        return false;
    }

    @Override
    public OrderDetails find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public List<OrderDetails> findAll() throws Exception {
        return null;
    }
}

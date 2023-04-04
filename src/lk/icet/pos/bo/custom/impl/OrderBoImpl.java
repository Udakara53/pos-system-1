package lk.icet.pos.bo.custom.impl;

import lk.icet.pos.bo.custom.OrderBo;
import lk.icet.pos.dao.DaoFactory;
import lk.icet.pos.dao.custom.OrderDao;
import lk.icet.pos.dto.OrderDto;
import lk.icet.pos.entity.Order;
import lk.icet.pos.enums.DaoType;

public class OrderBoImpl implements OrderBo {

    private OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);

    @Override
    public boolean saveOrder(OrderDto dto) throws Exception {
        return orderDao.save(
                new Order(dto.getOrderId(), dto.getCustomer(), dto.getDate(), dto.getTotal())
        );
    }
}

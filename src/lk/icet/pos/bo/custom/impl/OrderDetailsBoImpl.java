package lk.icet.pos.bo.custom.impl;

import lk.icet.pos.bo.custom.OrderDetailsBo;
import lk.icet.pos.dao.DaoFactory;
import lk.icet.pos.dao.custom.OrderDetailsDao;
import lk.icet.pos.dto.OrderDetailsDto;
import lk.icet.pos.entity.OrderDetails;
import lk.icet.pos.enums.DaoType;

public class OrderDetailsBoImpl implements OrderDetailsBo {
    private OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAILS);

    @Override
    public boolean saveOrderDetail(OrderDetailsDto dto) throws Exception {
        return orderDetailsDao.save(
                new OrderDetails(dto.getCode(), dto.getOrderId(),dto.getUnitPrice(),dto.getQty())
        );
    }
}

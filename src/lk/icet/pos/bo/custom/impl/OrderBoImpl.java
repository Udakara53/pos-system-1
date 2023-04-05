package lk.icet.pos.bo.custom.impl;

import lk.icet.pos.bo.BoFactory;
import lk.icet.pos.bo.custom.ItemBo;
import lk.icet.pos.bo.custom.OrderBo;
import lk.icet.pos.dao.DaoFactory;
import lk.icet.pos.dao.custom.ItemDao;
import lk.icet.pos.dao.custom.OrderDao;
import lk.icet.pos.dao.custom.OrderDetailsDao;
import lk.icet.pos.db.DBConnection;
import lk.icet.pos.dto.OrderDetailsDto;
import lk.icet.pos.dto.OrderDto;
import lk.icet.pos.entity.Order;
import lk.icet.pos.entity.OrderDetails;
import lk.icet.pos.enums.BOType;
import lk.icet.pos.enums.DaoType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderBoImpl implements OrderBo {

    private OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
    private OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAILS);
    private ItemBo itemBo = BoFactory.getInstance().getBo(BOType.ITEM);


    @Override
    public boolean saveOrder(OrderDto dto, List<OrderDetailsDto> details) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isOrderSaved = orderDao.save(
                    new Order(dto.getOrderId(), dto.getCustomer(), dto.getDate(), dto.getTotal())
            );
            if (isOrderSaved) {
                for (OrderDetailsDto d : details
                ) {
                    boolean isItemSaved = orderDetailsDao.save(
                            new OrderDetails(d.getCode(), d.getOrderId(), d.getUnitPrice(), d.getQty())
                    );
                    if (isOrderSaved) {
                        //update qty
                        boolean b = itemBo.updateQty(d.getCode(), d.getQty());
                        if (!b) {
                            connection.rollback();
                            return false;
                        }
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.commit();
            connection.setAutoCommit(true);
        }return true;
    }

    @Override
    public String generateOrderId() throws SQLException, ClassNotFoundException {
        return orderDao.generateOrderId();
    }
}

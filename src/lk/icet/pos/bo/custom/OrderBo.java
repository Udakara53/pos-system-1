package lk.icet.pos.bo.custom;

import lk.icet.pos.bo.SuperBo;
import lk.icet.pos.dto.OrderDto;

import java.sql.SQLException;

public interface OrderBo extends SuperBo {
    public boolean saveOrder(OrderDto dto) throws Exception;

    public String generateOrderId() throws SQLException, ClassNotFoundException;
}

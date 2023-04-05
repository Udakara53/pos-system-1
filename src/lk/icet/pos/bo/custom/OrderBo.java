package lk.icet.pos.bo.custom;

import lk.icet.pos.bo.SuperBo;
import lk.icet.pos.dto.OrderDetailsDto;
import lk.icet.pos.dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {
    public boolean saveOrder(OrderDto dto, List<OrderDetailsDto> details) throws Exception;

    public String generateOrderId() throws SQLException, ClassNotFoundException;
}

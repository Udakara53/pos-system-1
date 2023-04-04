package lk.icet.pos.bo.custom;

import lk.icet.pos.bo.SuperBo;
import lk.icet.pos.dto.OrderDto;

public interface OrderBo extends SuperBo {
    public boolean saveOrder(OrderDto dto) throws Exception;
}

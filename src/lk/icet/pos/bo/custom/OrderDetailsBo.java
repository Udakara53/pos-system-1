package lk.icet.pos.bo.custom;

import lk.icet.pos.dto.OrderDetailsDto;

public interface OrderDetailsBo {
    public boolean saveOrderDetail(OrderDetailsDto dto) throws Exception;
}

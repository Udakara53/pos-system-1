package lk.icet.pos.bo.custom;

import lk.icet.pos.bo.SuperBo;
import lk.icet.pos.dao.custom.CustomerDao;
import lk.icet.pos.dto.CustomerDto;

import java.util.List;

public interface CustomerBo  extends SuperBo {
    public boolean saveCustomer(CustomerDto dto) throws Exception;

    public boolean updateCustomer(CustomerDto dto) throws Exception;

    public CustomerDto findCustomer(String id) throws Exception;

    public boolean deleteCustomer(String id) throws Exception;

    public List<CustomerDto> findAllCustomers(String id) throws Exception;
}

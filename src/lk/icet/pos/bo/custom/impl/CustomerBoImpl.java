package lk.icet.pos.bo.custom.impl;

import lk.icet.pos.bo.custom.CustomerBo;
import lk.icet.pos.dao.custom.CustomerDao;
import lk.icet.pos.dao.custom.impl.CustomerDaoImpl;
import lk.icet.pos.dto.CustomerDto;
import lk.icet.pos.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao customerDao = new CustomerDaoImpl();
    
    @Override
    public boolean saveCustomer(CustomerDto dto) throws Exception {
        return customerDao.save(
                new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary())
        );

    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws Exception {
        return customerDao.update(
                new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary())
        );
    }

    @Override
    public CustomerDto findCustomer(String id) throws Exception {
        Customer c=customerDao.find(id);
        if (c!=null){
            return (new CustomerDto(c.getId(),c.getName(),c.getAddress(),c.getSalary()));
        }return null;

    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDao.delete(id);
    }

    @Override
    public List<CustomerDto> findAllCustomers(String id) throws Exception {
        List<Customer> customerList =  customerDao.findAll();
        List<CustomerDto> customerDtoList =new ArrayList<>();
        for (Customer c:customerList
             ) {
            customerDtoList.add(new CustomerDto(c.getId(),c.getName(),c.getAddress(),c.getSalary()));
        }
        return customerDtoList;
    }
}

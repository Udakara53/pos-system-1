package lk.icet.pos.dao.custom.impl;

import lk.icet.pos.dao.custom.CustomerDao;
import lk.icet.pos.entity.Customer;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer customer) throws Exception {
        return false;
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return false;
    }

    @Override
    public Customer find(String s) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        return null;
    }
}

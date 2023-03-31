package lk.icet.pos.dao.custom.impl;

import lk.icet.pos.dao.CrudUtil;
import lk.icet.pos.dao.custom.CustomerDao;
import lk.icet.pos.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer c) throws Exception {
        return CrudUtil.execute("INSERT INTO customer VALUES(?,?,?,?)",
                c.getId(),c.getName(),c.getAddress(),c.getSalary()
        );
    }

    @Override
    public boolean update(Customer c) throws Exception {
        return CrudUtil.execute("UPDATE customer SET name=?,address=?,salary=? WHERE id=?",
                c.getName(),c.getAddress(),c.getSalary(),c.getId()
        );
    }

    @Override
    public Customer find(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT*FROM customer WHERE id=?",id);
        if (resultSet.next()){
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }else{
            return null;
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM customer WHERE id=?",id);
    }

    @Override
    public List<Customer> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT*FROM customer");
        ArrayList<Customer> customerArrayList=new ArrayList<>();
        while (resultSet.next()){
            customerArrayList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }return customerArrayList;
    }
}

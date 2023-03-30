package lk.icet.pos.dao;

import lk.icet.pos.db.DBConnection;
import lk.icet.pos.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessCode {
    //========================Customer Manage Code=================

    public boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("INSERT INTO customer VALUES(?,?,?,?)",
                c.getId(),c.getName(),c.getAddress(),c.getSalary()
                );
    }
   public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("UPDATE customer SET name=?,address=?,salary=? WHERE id=?",
               c.getName(),c.getAddress(),c.getSalary(),c.getId()
               );
    }
    public Customer findCustomer(String id) throws ClassNotFoundException, SQLException {
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
   public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
       return CrudUtil.execute("DELETE FROM customer WHERE id=?",id);

    }
    public List<Customer> allCustomer(Customer c) throws ClassNotFoundException, SQLException {
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

    //========================Customer Manage Code=================

}

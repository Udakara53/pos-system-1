package lk.icet.pos.dao;

import lk.icet.pos.entity.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataAccessCode {
    //========================Customer Manage Code=================

    public boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos-system-1",
                "root","1234");
        String sql ="INSERT INTO customer VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,c.getId());
        preparedStatement.setString(2,c.getName());
        preparedStatement.setString(3,c.getAddress());
        preparedStatement.setDouble(4,c.getSalary());
        return preparedStatement.executeUpdate()>0;

    }
   /* public boolean updateCustomer(Customer c){return true;}
    public Customer findCustomer(String id){}
    public boolean deleteCustomer(String id){return true;}
    public List<Customer> allCustomer(Customer c){}*/

    //========================Customer Manage Code=================

}

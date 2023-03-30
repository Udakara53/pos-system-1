package lk.icet.pos.dao;

import lk.icet.pos.entity.Customer;

import java.sql.*;
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
   public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
       Class.forName("com.mysql.cj.jdbc.Driver");
       Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos-system-1",
               "root","1234");
       String sql ="UPDATE customer SET name=?,address=?,salary=? WHERE id=?";
       PreparedStatement preparedStatement = connection.prepareStatement(sql);
       preparedStatement.setString(1,c.getName());
       preparedStatement.setString(2,c.getAddress());
       preparedStatement.setDouble(3,c.getSalary());
       preparedStatement.setString(4,c.getId());
       return preparedStatement.executeUpdate()>0;
    }
    public Customer findCustomer(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos-system-1",
                "root","1234");
        String sql ="SELECT*FROM customer WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
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
       Class.forName("com.mysql.cj.jdbc.Driver");
       Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos-system-1",
               "root","1234");
       String sql ="DELETE FROM customer WHERE id=?";
       PreparedStatement preparedStatement = connection.prepareStatement(sql);
       preparedStatement.setString(1,id);
       return preparedStatement.executeUpdate()>0;

    }
    public List<Customer> allCustomer(Customer c) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos-system-1",
                "root","1234");
        String sql ="SELECT*FROM customer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
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

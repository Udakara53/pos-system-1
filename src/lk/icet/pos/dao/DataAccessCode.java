package lk.icet.pos.dao;
import javafx.scene.control.Alert;
import lk.icet.pos.dao.util.PasswordConfig;
import lk.icet.pos.entity.Customer;
import lk.icet.pos.entity.Item;
import lk.icet.pos.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DataAccessCode {
    //========================Start Customers Manage Code=================

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
    public List<Customer> allCustomer() throws ClassNotFoundException, SQLException {
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

    //========================End Customers Manage Code=================

    //========================Start Users Manage Code=======================

    public void saveUser() throws SQLException, ClassNotFoundException {
        ResultSet countSet = CrudUtil.execute("SELECT COUNT(*) FROM user");
        if(countSet.next()){
            int count =countSet.getInt(1);
            if (count==0){
                User user1 =(new User("tom",new PasswordConfig().encryptPassword("1234")));
                CrudUtil.execute("INSERT INTO user VALUES(?,?)",user1.getUsername(),user1.getPassword());
                User user2=(new User("anna",new PasswordConfig().encryptPassword("1234")));
                CrudUtil.execute("INSERT INTO user VALUES(?,?)",user2.getUsername(),user2.getPassword());
                User user3 =(new User("linda",new PasswordConfig().encryptPassword("1234")));
                CrudUtil.execute("INSERT INTO user VALUES(?,?)",user3.getUsername(),user3.getPassword());
            }
        }
    }

    public User findUser(String username) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT*FROM user WHERE user_name=?", username);
        if (resultSet.next()){
            return new User(resultSet.getString(1),resultSet.getString(2));
        }
        return null;
    }

    //========================End Users Manage Code========================

    //========================Start Items Manage Code========================
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO item VALUES(?,?,?,?)", i.getCode(), i.getDescription(), i.getQtyOnHand(), i.getUnitPrice());
    }
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE item SET description=?,qty_on_hand=?,unit_price=? WHERE code=?",i.getDescription(),i.getQtyOnHand(),i.getUnitPrice(),i.getCode());
    }
    public Item findItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT*FROM item WHERE code=?", code);
        if (rst.next()){
            return new Item(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
        }else{
            new Alert(Alert.AlertType.WARNING,"Item code is not found!").show();
            return null;
        }
    }
    public boolean deleteItem(Item i) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE code=?",i.getCode());
    }
    public List<Item> allItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> itemList = new ArrayList<>();
        ResultSet resultItem = CrudUtil.execute("SELECT*FROM item");
        while(resultItem.next()){
            itemList.add(new Item(
                    resultItem.getString(1),
                    resultItem.getString(2),
                    resultItem.getInt(3),
                    resultItem.getDouble(4)
            ));
        }return itemList;
    }

    //========================End Items Manage Code========================

}

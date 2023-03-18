package lk.icet.pos.db;

import lk.icet.pos.entity.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Arrays;

public class Database {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList();

    public static ArrayList<Item> items =new ArrayList();

    public static ArrayList<Order> orders = new ArrayList();



    static{
        users.add(new User("tom",encryptPassword("1234")));
        users.add(new User("anna",encryptPassword("1234")));
        users.add(new User("linda",encryptPassword("1234")));

        Customer customer1 = new Customer("C001","Nimal","Panadura",25000);
        Customer customer2 = new Customer("C002","Lasath","Galle",28000);
        Customer customer3 = new Customer("C003","Kamal","Ratnapura",35000);

        Item item1 = new Item("D-001","Description 1",25,2500);
        Item item2 = new Item("D-002","Description 2",25,4568);
        Item item3 = new Item("D-003","Description 3",20,3000);
        Item item4 = new Item("D-004","Description 4",20,5884);

        items.addAll(
                Arrays.asList(item1,item2,item3,item4)
        );
        customers.addAll(
                Arrays.asList(customer1,customer2,customer3)
        );
        //System.out.println(users);
    }
    private static String encryptPassword(String rowPassword){
        return BCrypt.hashpw(rowPassword,BCrypt.gensalt());
    }

}

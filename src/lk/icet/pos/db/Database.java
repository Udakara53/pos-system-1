package lk.icet.pos.db;

import lk.icet.pos.entity.User;

import java.util.ArrayList;

public class Database {
    public static ArrayList<User> users = new ArrayList();

    static{
        users.add(new User("tom","1234"));
        users.add(new User("anna","1234"));
        users.add(new User("linda","1234"));
    }

}

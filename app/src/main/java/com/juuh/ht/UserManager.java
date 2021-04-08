package com.juuh.ht;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> UserList;
    private static UserManager um_instance = null;
    private UserManager(){
        UserList = new ArrayList<User>();
    }

    public static UserManager getInstance(){
        if (um_instance == null){
            um_instance = new UserManager();
        }
        return um_instance;
    }

    public ArrayList<User> addUser(User user){
        UserList.add(user);
        return UserList;
    }
    public ArrayList<User> removeUser(User user){
        UserList.remove(user);
        return UserList;
    }
}

package com.god.haircutmanager.Model;

import com.orm.SugarRecord;// quản lý csdl sq lite của android studio

import java.util.List;

public class User extends SugarRecord {
    String username;
    String password;
    String role;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static void createDefaultAdminUser() {
        if (!isAccountExist("admin")) {
            User adminUser = new User("admin", "admin", "admin");
            adminUser.save();
        }
    }
    public static boolean isAccountExist(String username) {
        List<User> userList = User.find(User.class, "username = ?", username);
        return !userList.isEmpty();
    }

    public static boolean isValidCredentialInfo(String username, String password) {
        List<User> userList = User.find(User.class, "username = ? and password = ?", username, password);

        return !userList.isEmpty();
    }
}

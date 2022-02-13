package com.company.controller;

import com.company.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagement implements ReadFile,WriteFile {

    private List<User> users = new ArrayList<>();
    private static UserManagement instance;
    public static UserManagement getUserManagement(){
        if (instance ==null){
            instance =new UserManagement();
        }
        return instance;
    }
    public UserManagement() {
        File file = new File("user.txt");
        if (file.exists()) {
            try {
                readFile("user.txt");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void displayAll(String role) {
        try {
            readFile("user.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (User user : users) {
            if (user.getRole().equals(role)) {
                System.out.println(user);
            }
        }
    }

    public void deleteMember(String username){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLoginName().equals(username) && users.get(i).getRole().equals("user")){
                users.remove(users.get(i));
            }
        }

    }

    public void register(User user) {
        this.users.add(user);
        try {
            writeFile("user.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.users = (List<User>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.users);
    }

    public boolean checkUsernameExist(String username) {//Kiểm tra xem username đã tồn tại hay chưa
        boolean isExisted = false;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getLoginName())) {
                isExisted = true;
                break;
            }
        }
        return isExisted;
    }

    public boolean checkUserLogin(String username, String password) {//Kiểm tra xem user đăng nhập có tồn tại hay không
        boolean isLogin = false;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getLoginName()) && password.equals(users.get(i).getPassWord())) {
                isLogin = true;
                break;
            }
        }
        return isLogin;
    }
   public boolean checkadmin(String username,String password){ //kiểm tra xem có phải là admin hay không.
       boolean isAdmin = false;
       for (int i = 0; i < users.size(); i++) {
           if (username.equals(users.get(i).getLoginName()) && password.equals(users.get(i).getPassWord())&&"admin".equals(users.get(i).getRole())) {
               isAdmin = true;
               break;
           }
       }
       return isAdmin;
   }

}

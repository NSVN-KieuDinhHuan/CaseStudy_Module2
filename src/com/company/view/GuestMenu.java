package com.company.view;
import com.company.controller.DictionaryManagement;
import com.company.controller.UserManagement;
import com.company.model.User;

import java.io.IOException;
import java.util.Scanner;

import static com.company.controller.DictionaryManagement.ADMIN_ROLE;
import static com.company.controller.DictionaryManagement.MEMBER_ROLE;

public class GuestMenu {
    DictionaryManagement dictionaryManagement = DictionaryManagement.getdictionaryManagement();
    UserManagement userManagement=UserManagement.getUserManagement();
    Usermenu usermenu=new Usermenu();
    AdminMenu adminMenu=new AdminMenu();
    Scanner scanner=new Scanner(System.in);
    public void run() throws IOException, ClassNotFoundException {
        int choice = -1;
        do {
            menu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    usermenu.lookupDictionary();
                    break;
                }
                case 2: {
                    doLogin();
                    break;
                }
                case 3:{
                    doRegister();
                    break;
                }
            }
        } while (choice != 0);
    }



    private void doLogin() throws IOException, ClassNotFoundException {
        System.out.println("Nhập username:");
        scanner.nextLine();
        String username = scanner.nextLine();
        System.out.println("Nhập password:");
        String password = scanner.nextLine();
        boolean isLogin = userManagement.checkUserLogin(username, password);
        if (isLogin) {
            System.out.println("Đăng nhập thành công!");
            if(userManagement.checkadmin(username, password)){
                adminMenu.run();
            }else {
                usermenu.run();
            }
        } else {
            System.err.println("Username hoặc password không đúng!");
        }
    }

    private void doRegister() throws IOException, ClassNotFoundException {
        String role="";
        int choice = -1;
        do {
            System.out.println("___Đăng ký tài khoản___");
            System.out.println("1.Đăng ký member");
            System.out.println("2.Đăng ký admin");
            System.out.println("3.Quay lại");
            System.out.println("Nhập Lựa chọn của bạn:");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    role=MEMBER_ROLE;
                    break;
                }
                case 2: {
                    role = getAdminRole(role);
                    break;
                }
                case 3:{
                    run();
                    break;
                }
            }

        } while (role.equals(""));
        System.out.println("Nhâp Họ và tên");
        String MemberName=scanner.nextLine();
        System.out.println("Trình độ tiếng nhật");
        String JapaneseLevel=scanner.nextLine();
        String username = inputUsername();
        String password = inputPassword();
        User user = new User(username, password,role,MemberName,JapaneseLevel);
        userManagement.register(user);
        System.out.println("Đăng ký thành công");
    }

    private String getAdminRole(String role) {
        System.out.println("Nhập Mật khẩu để đăng ký admin");
        String adminPass= scanner.nextLine();
        if (adminPass.equals("CodeGym")){
            role =ADMIN_ROLE;
        }else {
            System.err.println("Mật khẩu không đúng. bạn không thể trở thành admin");
        }
        return role;
    }

    private String inputPassword() {
        String password;
        do {
            System.out.println("Nhập mật khẩu (6-12 ký tự):");
            password = scanner.nextLine();
            if (password.length() < 6) {
                System.err.println("Mật khẩu phải có ít nhất 6 ký tự!");
            } else if (password.length() > 12) {
                System.err.println("Mật khẩu chỉ được phép tối đa 12 ký tự!");
            }
        } while (password.length() < 6 || password.length() > 12);
        return password;
    }

    private String inputUsername() {
        String username;
        do {
            System.out.println("Nhập tên tài khoản (6-12 ký tự):");
            username = scanner.nextLine();
            if (username.length() < 6) {
                System.err.println("Tài khoản phải có ít nhất 6 ký tự!");
            } else if (username.length() > 12) {
                System.err.println("Tài khoản chỉ được phép tối đa 12 ký tự!");
            } else if (userManagement.checkUsernameExist(username)) {
                System.err.println("Tài khoản này đã được đăng ký!");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (username.length() < 6 || username.length() > 12 || userManagement.checkUsernameExist(username));
        return username;
    }

    private void menu() {
        System.out.println("______Menu Of Guest______");
        System.out.println("1. Tra từ điển Nhật-Việt");
        System.out.println("2. Đăng nhập ");
        System.out.println("3. Đăng ký");
        System.out.println("0. Thoát ");
        System.out.println("Nhập lựa chọn của bạn:");
    }
}

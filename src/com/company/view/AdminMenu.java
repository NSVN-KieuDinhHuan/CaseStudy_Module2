package com.company.view;
import com.company.controller.DictionaryManagement;
import com.company.controller.UserManagement;

import java.io.IOException;
import java.util.Scanner;

import static com.company.controller.DictionaryManagement.ADMIN_ROLE;
import static com.company.controller.DictionaryManagement.MEMBER_ROLE;

public class AdminMenu {
    DictionaryManagement dictionaryManagement = DictionaryManagement.getdictionaryManagement();
    UserManagement userManagement=UserManagement.getUserManagement();
    Usermenu usermenu =new Usermenu();
    Scanner scanner =new Scanner(System.in);
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
                    usermenu.inputJapanword(ADMIN_ROLE);
                    break;
                } case 3:{
                    usermenu.removeWord(ADMIN_ROLE);
                    break;
                }
                case 4:{
                    usermenu.displayAllDictionary();
                    break;
                }

                case 5: {
                    checkAdditionWord();
                    break;
                }
                case 6: {
                    checkRemovalWord();
                }
                case 7: {
                    dictionaryManagement.removeAllSuggestion();
                    break;
                }
                case 8:{
                    userManagement.displayAllUser(MEMBER_ROLE);
                    break;
                }
                case 9:{
                    userManagement.displayAllUser(ADMIN_ROLE);
                    break;
                }
                case 10:{
                    removeMember();
                }
            }
        } while (choice != 0);
    }

    private void checkRemovalWord() throws IOException, ClassNotFoundException {
        int choice2 = -1;

        do {
            dictionaryManagement.displayRemovalSuggestion();
            System.out.println("chọn -1 để thoát?");
            System.out.println("Chấp thuận với từ số ?");
            scanner.nextLine();
            choice2 = scanner.nextInt();
            switch (choice2){
                case -1: {
                    break;
                } default: {
                    if (dictionaryManagement.getOfferRemovalList().size() > 0) {
                        dictionaryManagement.approveaRemoval(choice2);
                    } else {
                        System.err.println("lựa chọn lại");
                    }
                    break;
                }
            }
        } while (choice2!= -1);
    }

    private void checkAdditionWord() throws IOException, ClassNotFoundException {
        int  choice1=-1;
        do {
            dictionaryManagement.displayAdditionSuggestion();
            System.out.println("chọn -1 để thoát?");
            System.out.println("Chấp thuận với từ số ?");
            scanner.nextLine();
            choice1 = scanner.nextInt();
            switch (choice1){
                case -1:{
                    break;
                } default:{
                    if (dictionaryManagement.getOfferAdditionList().size() > 0) {
                        dictionaryManagement.approveAddition(choice1);
                    }else {
                        System.out.println("lựa chọn lại");
                    }
                    break;
                }
            }

        } while (choice1!=-1);
    }

    private void removeMember() throws IOException {
        System.out.println("Nhập tên đăng nhập của member muốn xóa");
        scanner.nextLine();
        String member=scanner.nextLine();
        userManagement.removeUser(member,MEMBER_ROLE);
        System.out.println("đã xóa member:"+member);
    }
    private void menu() {
        System.out.println("_______Admin menu______");
        System.out.println("1. Tra từ điển Nhật-Việt");
        System.out.println("2. Thêm từ ");
        System.out.println("3. Xóa Từ ");
        System.out.println("4. Hiện thị tất cả từ");
        System.out.println("5. Kiểm tra đề xuất Thêm từ của member");
        System.out.println("6. Kiểm tra đề xuất xóa từ của member");
        System.out.println("7. Xóa tất cả các đề xuất của member");
        System.out.println("8. Hiện thị tất cả member");
        System.out.println("9. Hiện thị tất cả các admin");
        System.out.println("10.Xóa member");
        System.out.println("0. Quay lại");
    }

}

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
            System.out.println("ch???n -1 ????? tho??t?");
            System.out.println("Ch???p thu???n v???i t??? s??? ?");
            scanner.nextLine();
            choice2 = scanner.nextInt();
            switch (choice2){
                case -1: {
                    break;
                } default: {
                    if (dictionaryManagement.getOfferRemovalList().size() > 0) {
                        dictionaryManagement.approveaRemoval(choice2);
                    } else {
                        System.err.println("l???a ch???n l???i");
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
            System.out.println("ch???n -1 ????? tho??t?");
            System.out.println("Ch???p thu???n v???i t??? s??? ?");
            scanner.nextLine();
            choice1 = scanner.nextInt();
            switch (choice1){
                case -1:{
                    break;
                } default:{
                    if (dictionaryManagement.getOfferAdditionList().size() > 0) {
                        dictionaryManagement.approveAddition(choice1);
                    }else {
                        System.out.println("l???a ch???n l???i");
                    }
                    break;
                }
            }

        } while (choice1!=-1);
    }

    private void removeMember() throws IOException {
        System.out.println("Nh???p t??n ????ng nh???p c???a member mu???n x??a");
        scanner.nextLine();
        String member=scanner.nextLine();
        userManagement.removeUser(member,MEMBER_ROLE);
        System.out.println("???? x??a member:"+member);
    }
    private void menu() {
        System.out.println("_______Admin menu______");
        System.out.println("1. Tra t??? ??i???n Nh???t-Vi???t");
        System.out.println("2. Th??m t??? ");
        System.out.println("3. X??a T??? ");
        System.out.println("4. Hi???n th??? t???t c??? t???");
        System.out.println("5. Ki???m tra ????? xu???t Th??m t??? c???a member");
        System.out.println("6. Ki???m tra ????? xu???t x??a t??? c???a member");
        System.out.println("7. X??a t???t c??? c??c ????? xu???t c???a member");
        System.out.println("8. Hi???n th??? t???t c??? member");
        System.out.println("9. Hi???n th??? t???t c??? c??c admin");
        System.out.println("10.X??a member");
        System.out.println("0. Quay l???i");
    }

}

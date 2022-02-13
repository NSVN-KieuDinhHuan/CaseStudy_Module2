package com.company.view;
import com.company.controller.DictionaryDataBase;
import com.company.controller.UserManagement;
import com.company.model.JapanVietnamDictionary;
import com.company.model.JapaneseWord;
import java.io.IOException;
import java.util.Scanner;

public class AdminMenu {
    DictionaryDataBase dictionaryDataBase = DictionaryDataBase.getdictionaryManagement();
    UserManagement userManagement=new UserManagement();
    Scanner scanner =new Scanner(System.in);
    public void run(){
        int choice = -1;
        do {
            menu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Nhập từ cần tra nghĩa");
                    scanner.nextLine();
                    String str= scanner.nextLine();
                    System.out.println(dictionaryDataBase.lookUpWord(str));
                    break;
                }
                case 2: {
                    inputJapanword();
                    break;
                }
                case 3:{
                    System.out.println("Nhập từ cần xóa");
                    String Word=scanner.nextLine();
                    dictionaryDataBase.removeWord(Word,"admin");
                    break;
                }
                case 4:{
                    dictionaryDataBase.displayAllWord();
                    break;
                }

                case 5:{

                    if (dictionaryDataBase.getAddTemporaryWord().size()>0) {
                        dictionaryDataBase.displayAddTemprorayWord();
                        System.out.println("Lựa chon từ số?");
                        scanner.nextLine();
                        int index = scanner.nextInt();
                        dictionaryDataBase.approveaAddWord(index);
                    }else {
                        System.err.println("Không có đề xuất nào của member");
                    }
                    break;

                }
                case 6:{
                    if (dictionaryDataBase.getRemoveTemporaryWord().size()>0) {
                        System.out.println("Danh sách đề xuất xóa từ của member");
                        dictionaryDataBase.displayRemoveTemprorayWord();
                        System.out.println("Lựa chon từ số?");
                        scanner.nextLine();
                        int index = scanner.nextInt();
                        dictionaryDataBase.approvearemoveWord(index);

                    }else {
                        System.err.println("không có đề xuất nào của member");
                    }
                    break;
                }
                case 7:{
                    try {
                        dictionaryDataBase.deletOfferOfMember();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 8:{
                    userManagement.displayAll("user");
                    break;
                }
                case 9:{
                    userManagement.displayAll("admin");
                    break;
                }
                case 10:{
                    System.out.println("Nhâp tên đăng nhập để xóa member");
                    scanner.nextLine();
                    String member=scanner.nextLine();
                    userManagement.deleteMember(member);
                    break;
                }
            }
        } while (choice != 0);
    }

    public void inputJapanword() {
        System.out.println("_____Thêm từ Tiếng nhật vào trong từ điển_______");
        System.out.println(" Thêm chữ Kanji");
        scanner.nextLine();
        String kanji=scanner.nextLine();
        System.out.println("Thêm chữ Hiragana");
        String hiragana=scanner.nextLine();
        System.out.println("Thêm cách đọc");
        String pronunciation=scanner.nextLine();
        System.out.println("Từ loại");
        String wordType=scanner.nextLine();
        System.out.println("Nghĩa là gì?");
        String meaning=scanner.nextLine();
        JapaneseWord japaneseWord=new JapaneseWord(kanji,hiragana,pronunciation);
        System.out.println("Ví Dụ về từ tiếng nhật đã thêm vào");
        String sentence= scanner.nextLine();
        System.out.println("Nghĩa của câu trên");
        String meaningOfSentence= scanner.nextLine();
        JapanVietnamDictionary japanVietnamDictionary= new JapanVietnamDictionary(japaneseWord,wordType,meaning,sentence,meaningOfSentence);
        dictionaryDataBase.addNewWord(japanVietnamDictionary,"admin");
    }

    private void removeMember() {
        System.out.println("Nhập tên đăng nhập cần xóa");
        String user=scanner.nextLine();
        userManagement.deleteMember(user);
    }

    private void menu() {
        System.out.println("_______Admin menu______");
        System.out.println("1. Tra từ điển Nhật-Việt");
        System.out.println("2. Thêm từ ");
        System.out.println("3. Xóa Từ ");
        System.out.println("4. Hiện thị tất cả từ");
        System.out.println("5. Phê duyet đề xuất Thêm từ");
        System.out.println("6. Phê Duyệt đề xuất xóa từ");
        System.out.println("7. Xóa tất cả các đề xuất của member");
        System.out.println("8. Hiện thị tất cả member");
        System.out.println("9. Hiện thị tất cả các admin");
        System.out.println("10.Xóa member");
        System.out.println("0. Quay lại");
    }

}

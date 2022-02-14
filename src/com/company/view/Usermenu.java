package com.company.view;

import com.company.controller.DictionaryManagement;
import com.company.model.JapanVietnamDictionary;
import com.company.model.JapaneseWord;

import java.io.IOException;
import java.util.Scanner;

import static com.company.controller.DictionaryManagement.MEMBER_ROLE;

public class Usermenu{
    DictionaryManagement dictionaryManagement = DictionaryManagement.getdictionaryManagement();
    Scanner scanner =new Scanner(System.in);
   public void run() throws IOException, ClassNotFoundException {
        int choice = -1;
        do {
            menu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    lookupDictionary();
                    break;
                }
                case 2: {
                    inputJapanword(MEMBER_ROLE);
                    break;
                }
                case 3:{
                    removeWord(MEMBER_ROLE);
                    break;
                }
                case 4:{
                    displayAllDictionary();
                    break;
                }

            }
        } while (choice != 0);

    }

    void displayAllDictionary() throws IOException, ClassNotFoundException {
        dictionaryManagement.displayAllWord();
    }

    public void lookupDictionary() {
        System.out.println("Nhập từ cần tra nghĩa");
        String str= scanner.nextLine();
        System.out.println(dictionaryManagement.lookUpWord(str));
    }

    public void removeWord(String role) throws IOException {
        System.out.println("Nhập từ cần xóa");
        String Word=scanner.nextLine();
        dictionaryManagement.removeWord(Word,role);
    }

    public void inputJapanword(String role) throws IOException, ClassNotFoundException {
        System.out.println("_____Thêm từ Tiếng nhật vào trong từ điển_______");
        System.out.println(" Thêm chữ Kanji");
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
        dictionaryManagement.addNewWord(japanVietnamDictionary,role);
    }

    private void menu() {
        System.out.println("_______Member menu______");
        System.out.println("1. Tra từ điển Nhật-Việt");
        System.out.println("2. Thêm từ ");
        System.out.println("3. Xóa Từ ");
        System.out.println("4. Hiện thị tất cả từ trong tu dien");
        System.out.println("0. Thoát ");
    }

}

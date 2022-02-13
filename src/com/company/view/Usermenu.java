package com.company.view;

import com.company.controller.DictionaryDataBase;
import com.company.model.JapanVietnamDictionary;
import com.company.model.JapaneseWord;
import java.util.Scanner;

public class Usermenu{
    DictionaryDataBase dictionaryDataBase = DictionaryDataBase.getdictionaryManagement();
    Scanner scanner =new Scanner(System.in);
   public void run(){
        int choice = -1;
        do {
            menu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    lookupDictionary();
                    break;
                }
                case 2: {
                    inputJapanword();
                    break;
                }
                case 3:{
                    removeWord();
                    break;
                }
                case 4:{
                    dictionaryDataBase.displayAllWord();
                    break;
                }

            }
        } while (choice != 0);

    }

    public void lookupDictionary() {
        System.out.println("Nhập từ cần tra nghĩa");
        scanner.nextLine();
        String str= scanner.nextLine();
        System.out.println(dictionaryDataBase.lookUpWord(str));
    }

    public void removeWord() {
        System.out.println("Nhập từ cần xóa");
        scanner.nextLine();
        String Word=scanner.nextLine();
        dictionaryDataBase.removeWord(Word,"user");
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
        dictionaryDataBase.addNewWord(japanVietnamDictionary,"user");
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

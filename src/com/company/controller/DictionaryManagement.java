package com.company.controller;

import com.company.model.JapanVietnamDictionary;
import com.company.model.JapaneseWord;

import java.io.*;
import java.util.*;

public class DictionaryManagement implements ReadFile,WriteFile{
    private static final String DICTIONARY_DATA_STORAGE ="DictinoryDataBase.txt";
    private static final String OFFER_ADDITION_STORAGE ="OfferAdditionStorage.txt";
    private static final String OFFER_REMOVAL_STORAGE ="OfferRemovalStorage.txt";
    public static final String ADMIN_ROLE ="admin";
    public static final String MEMBER_ROLE ="member";
    private HashMap<JapaneseWord, JapanVietnamDictionary> dictionaryHashMap = new HashMap<>();
    private Set<JapaneseWord> japaneseWords=dictionaryHashMap.keySet();
    private List<JapanVietnamDictionary> offerAdditionList = new ArrayList<>();
    private List<JapanVietnamDictionary> offerRemovalList = new ArrayList<>();
    private static DictionaryManagement instance;
    public static DictionaryManagement getdictionaryManagement(){
        if (instance ==null){
            instance =new DictionaryManagement();
        }
        return instance;
    }

    public List<JapanVietnamDictionary> getOfferAdditionList() {
        return offerAdditionList;
    }

    public List<JapanVietnamDictionary> getOfferRemovalList() {
        return offerRemovalList;
    }

    public void setOfferRemovalList(List<JapanVietnamDictionary> offerRemovalList) {
        this.offerRemovalList = offerRemovalList;
    }

    public void setOfferAdditionList(List<JapanVietnamDictionary> offerAdditionList) {
        this.offerAdditionList = offerAdditionList;
    }

    public DictionaryManagement()  {

        File file1 = new File(DICTIONARY_DATA_STORAGE);
        if (file1.exists()) {
            try {
                readFile(DICTIONARY_DATA_STORAGE);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try {
                writeFile(DICTIONARY_DATA_STORAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file2 = new File(OFFER_ADDITION_STORAGE);
        if (file2.exists()) {
            try {
                readFile(OFFER_ADDITION_STORAGE);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try {
                writeFile(OFFER_ADDITION_STORAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file3 = new File(OFFER_REMOVAL_STORAGE);
        if (file3.exists()) {
            try {
                readFile(OFFER_REMOVAL_STORAGE);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try {
                writeFile(OFFER_REMOVAL_STORAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   public void addNewWord(JapanVietnamDictionary japaneseVietnamese,String role) throws IOException, ClassNotFoundException {
       boolean checkExsistWord=checkwordExist(japaneseVietnamese.getJapaneseWord().getPronunciation());
       if (checkExsistWord==false) {
           if (role.equals(ADMIN_ROLE)) {
               dictionaryHashMap.put(japaneseVietnamese.getJapaneseWord(), japaneseVietnamese);
               this.writeFile(DICTIONARY_DATA_STORAGE);
           }else if (role.equals(MEMBER_ROLE)) {
               offerAdditionList.add(japaneseVietnamese);
               this.writeFile(OFFER_ADDITION_STORAGE);
           }

       }else {
           System.out.println("Đã có từ này trong từ điển");
       }


   }
  public JapanVietnamDictionary lookUpWord(String keyword) {
      JapaneseWord key = null;
      if (this.japaneseWords != null) {
          for (JapaneseWord japaneseWord : japaneseWords) {
              if (japaneseWord.getKanji().equals(keyword) || japaneseWord.getPronunciation().equals(keyword) || japaneseWord.getHiragana().equals(keyword)) {
                  key = japaneseWord;
                  break;
              }
          }
          if (key == null) {
              return null;
          } else {
              return dictionaryHashMap.get(key);
          }
      } else {
          return null;
      }
  }

    public void removeWord(String keyword,String role) throws IOException {
        JapaneseWord key=null;
        for (JapaneseWord japaneseWord : japaneseWords) {
            if (japaneseWord.getKanji().equals(keyword) || japaneseWord.getPronunciation().equals(keyword)  || japaneseWord.getHiragana().equals(keyword)) {
                key=japaneseWord;
                break;
            }
        }
        if (key!=null) {
            if (role.equals(ADMIN_ROLE)) {
                dictionaryHashMap.remove(key);
                System.out.println("Đã xóa từ :" + keyword);
                writeFile(DICTIONARY_DATA_STORAGE);
            }else if(role.equals(MEMBER_ROLE)){
                offerRemovalList.add(dictionaryHashMap.get(key));
                writeFile(OFFER_REMOVAL_STORAGE);
                System.out.println("Đã  đề xuất xóa từ :" + keyword +" cho admin");

            }
        }else {
            System.out.println("không cần xóa vì từ đó chưa từng có trong từ điển");
        }

    }
    public void displayAllWord() throws IOException, ClassNotFoundException {
        readFile(DICTIONARY_DATA_STORAGE);
        if (japaneseWords.size()>0) {
            for (JapaneseWord japaneseWord : japaneseWords) {
                System.out.println(dictionaryHashMap.get(japaneseWord));
            }
        }else {
            System.err.println("Không có từ nào trong từ điển");
        }
    }

    public  void displayAdditionSuggestion() throws IOException, ClassNotFoundException {
        readFile(OFFER_ADDITION_STORAGE);
        if (offerAdditionList.size()!=0) {
            System.out.println("Danh sách đề xuất thêm từ");
            for (int i = 0; i < offerAdditionList.size(); i++) {
                System.out.println("Từ "+i+ offerAdditionList.get(i));
            }
        }else {
            System.err.println("Không có đề xuất nào");
        }
    }
    public  void displayRemovalSuggestion() throws IOException, ClassNotFoundException {
        readFile(OFFER_REMOVAL_STORAGE);
        System.out.println("Danh sách đề xuất Xóa từ");
        if (offerRemovalList.size()!=0) {
            for (int i = 0; i < offerRemovalList.size(); i++) {
                System.out.println("Từ "+i+offerRemovalList.get(i));
            }
        }else {
            System.err.println("Không có đề xuất nào");
        }
    }
    public void approveAddition(int index){
        if (offerAdditionList.size()!=0) {
            dictionaryHashMap.put(offerAdditionList.get(index).getJapaneseWord(), offerAdditionList.get(index));
            offerAdditionList.remove(index);
            System.out.println("phê duyệt thành công");
            try {
                this.writeFile(OFFER_ADDITION_STORAGE);
                writeFile(DICTIONARY_DATA_STORAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("không có từ nào");
        }
    }
    public void approveaRemoval(int index) throws IOException {
        if (offerRemovalList.size()!=0) {
            this.removeWord(offerRemovalList.get(index).getJapaneseWord().getKanji(),ADMIN_ROLE);
            offerRemovalList.remove(index);
            try {
                writeFile(OFFER_REMOVAL_STORAGE);
                writeFile(DICTIONARY_DATA_STORAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("không có từ nào");
        }
    }

    public void removeAllSuggestion() throws IOException {
        offerRemovalList.clear();
        offerAdditionList.clear();
        this.writeFile(OFFER_REMOVAL_STORAGE);
        this.writeFile(OFFER_ADDITION_STORAGE);
    }
    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        if (path.equals(DICTIONARY_DATA_STORAGE)) {
            this.dictionaryHashMap = (HashMap<JapaneseWord, JapanVietnamDictionary>) ois.readObject();
            this.japaneseWords = this.dictionaryHashMap.keySet();
        }else if (path.equals(OFFER_ADDITION_STORAGE)){
            this.offerAdditionList = (List<JapanVietnamDictionary>) ois.readObject();
        }else {
            this.offerRemovalList =(List<JapanVietnamDictionary>) ois.readObject();
        }
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        if (path.equals(DICTIONARY_DATA_STORAGE)) {
            oos.writeObject(this.dictionaryHashMap);
        }else if (path.equals(OFFER_ADDITION_STORAGE)){
            oos.writeObject(this.offerAdditionList);
        }else {
            oos.writeObject(this.offerRemovalList);
        }
    }

    public boolean checkwordExist(String str) {//Kiểm tra xem từ đó đã tồn tại hay chưa;
        boolean isExisted = false;
        for (JapaneseWord word : japaneseWords) {
            if (word.getKanji() == str || word.getPronunciation() == str || word.getHiragana() == str) {
                isExisted = true;
                break;
            }
        }
        return isExisted;
    }
}

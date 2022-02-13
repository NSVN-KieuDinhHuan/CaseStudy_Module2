package com.company.controller;

import com.company.model.JapanVietnamDictionary;
import com.company.model.JapaneseWord;

import java.io.*;
import java.util.*;

public class DictionaryDataBase implements ReadFile,WriteFile{
    private HashMap<JapaneseWord, JapanVietnamDictionary> dictionaryHashMap = new HashMap<>();
    private Set<JapaneseWord> japaneseWords=dictionaryHashMap.keySet();
    private List<JapanVietnamDictionary> addTemporaryWord = new ArrayList<>();
    private List<JapanVietnamDictionary> removeTemporaryWord = new ArrayList<>();
    private static DictionaryDataBase instance;
    public static DictionaryDataBase getdictionaryManagement(){
        if (instance ==null){
            instance =new DictionaryDataBase();
        }
        return instance;
    }

    public List<JapanVietnamDictionary> getAddTemporaryWord() {
        return addTemporaryWord;
    }

    public List<JapanVietnamDictionary> getRemoveTemporaryWord() {
        return removeTemporaryWord;
    }

    public void setRemoveTemporaryWord(List<JapanVietnamDictionary> removeTemporaryWord) {
        this.removeTemporaryWord = removeTemporaryWord;
    }

    public void setAddTemporaryWord(List<JapanVietnamDictionary> addTemporaryWord) {
        this.addTemporaryWord = addTemporaryWord;
    }

    public DictionaryDataBase() {
        File file1 = new File("DictinoryDataBase.txt");
        if (file1.exists()) {
            try {
                readFile("DictinoryDataBase.txt");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        File file2 = new File("addTemporaryFile.txt");
        if (file2.exists()) {
            try {
                readFile("addTemporaryFile.txt");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        File file3 = new File("removeTemporaryFile.txt");
        if (file3.exists()) {
            try {
                readFile("removeTemporaryFile.txt");

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
   public void addNewWord(JapanVietnamDictionary japaneseVietnamese,String role) {
       JapanVietnamDictionary checkExsistWord = lookUpWord(japaneseVietnamese.getJapaneseWord().getPronunciation());
       if (checkExsistWord==null) {
           if (role.equals("admin")) {
               dictionaryHashMap.put(japaneseVietnamese.getJapaneseWord(), japaneseVietnamese);
               try {
                   this.writeFile("DictinoryDataBase.txt");
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }else if (role.equals("user")){
               addTemporaryWord.add(japaneseVietnamese);
               try {
                   this.writeFile("addTemporaryFile.txt");
               } catch (IOException e) {
                   e.printStackTrace();
               }
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

    public void removeWord(String keyword,String role) {
        JapaneseWord key=null;
        for (JapaneseWord japaneseWord : japaneseWords) {
            if (japaneseWord.getKanji().equals(keyword) || japaneseWord.getPronunciation().equals(keyword)  || japaneseWord.getHiragana().equals(keyword)) {
                key=japaneseWord;
                break;
            }
        }
        if (key!=null) {
            if (role.equals("admin")) {
                dictionaryHashMap.remove(key);
                System.out.println("đã xóa từ :" + keyword);
            }else if(role.equals("user")){
                removeTemporaryWord.add(dictionaryHashMap.get(key));
                try {
                    writeFile("removeTemporaryFile.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("không cần xóa vì từ đó chưa từng có trong từ điển");
        }
    }
    public void displayAllWord(){
        for (JapaneseWord japaneseWord : japaneseWords) {
            System.out.println(dictionaryHashMap.get(japaneseWord));
        }
    }

    public  void displayAddTemprorayWord(){
        if (addTemporaryWord.size()!=0) {
            System.out.println("Danh sách đề xuất thêm từ");
            for (int i = 0; i < addTemporaryWord.size(); i++) {
                System.out.println("Từ số "+i+addTemporaryWord.get(i));
            }
        }else {
            System.err.println("Không có đề xuất nào");
        }
    }
    public  void displayRemoveTemprorayWord(){
        if (removeTemporaryWord.size()!=0) {
            for (int i = 0; i < removeTemporaryWord.size(); i++) {
                System.out.println(removeTemporaryWord.get(i));
            }
        }
    }
    public void approveaAddWord(int index){
        if (addTemporaryWord.size()!=0) {
            dictionaryHashMap.put(addTemporaryWord.get(index).getJapaneseWord(), addTemporaryWord.get(index));
            addTemporaryWord.remove(index);
            System.out.println("phê duyệt thành công");
            try {
                this.writeFile("addTemporaryFile.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("không có từ nào");
        }
    }
    public void approvearemoveWord(int index){
        if (addTemporaryWord.size()!=0) {
           dictionaryHashMap.remove(removeTemporaryWord.get(index).getJapaneseWord(),removeTemporaryWord.get(index));
            removeTemporaryWord.remove(index);
            System.out.println("phê duyệt thành công");
            try {
                this.writeFile("removeTemporaryFile.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("không có từ nào");
        }
    }

    public void deletOfferOfMember() throws IOException {
        removeTemporaryWord.clear();
        addTemporaryWord.clear();
        this.writeFile("removeTemporaryFile.txt");
        this.writeFile("addTemporaryFile.txt");
    }
    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        if (path.equals("DictinoryDataBase.txt")) {
            this.dictionaryHashMap = (HashMap<JapaneseWord, JapanVietnamDictionary>) ois.readObject();
            this.japaneseWords = this.dictionaryHashMap.keySet();
        }else if (path.equals("addTemporaryFile.txt")){
            this.addTemporaryWord = (List<JapanVietnamDictionary>) ois.readObject();
        }else {
            this.removeTemporaryWord=(List<JapanVietnamDictionary>) ois.readObject();
        }
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        if (path.equals("DictinoryDataBase.txt")) {
            oos.writeObject(this.dictionaryHashMap);
        }else if (path.equals("addTemporaryFile.txt")){
            oos.writeObject(this.addTemporaryWord);
        }else {
            oos.writeObject(this.removeTemporaryWord);
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

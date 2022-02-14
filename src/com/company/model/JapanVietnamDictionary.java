package com.company.model;

import java.io.Serializable;

public class JapanVietnamDictionary  implements Serializable  {
     private JapaneseWord japaneseWord;
     private String wordType;
     private String meaning;
     private String sentence;
     private String meaningOfSentence;
     public JapanVietnamDictionary() {
     }

     public JapanVietnamDictionary(JapaneseWord japaneseWord, String wordType, String meaning, String sentence, String meaningOfSentence) {
          this.japaneseWord = japaneseWord;
          this.wordType = wordType;
          this.meaning = meaning;
          this.sentence = sentence;
          this.meaningOfSentence = meaningOfSentence;
     }

     public JapaneseWord getJapaneseWord() {
          return japaneseWord;
     }

     public void setJapaneseWord(JapaneseWord japaneseWord) {
          this.japaneseWord = japaneseWord;
     }

     public String getWordType() {
          return wordType;
     }

     public void setWordType(String wordType) {
          this.wordType = wordType;
     }

     public String getMeaning() {
          return meaning;
     }

     public void setMeaning(String meaning) {
          this.meaning = meaning;
     }

     public String getSentence() {
          return sentence;
     }

     public void setSentence(String sentence) {
          this.sentence = sentence;
     }

     public String getMeaningOfSentence() {
          return meaningOfSentence;
     }

     public void setMeaningOfSentence(String meaningOfSentence) {
          this.meaningOfSentence = meaningOfSentence;
     }

     @Override
     public String toString() {
          return   "----------------------------"+ '\n' +
                  japaneseWord  +
                  "*" + wordType + '\n' +
                  "Nghĩa:"+ meaning + '\n' +
                  " " + sentence + '\n' +
                  " " + meaningOfSentence + '\n';

     }
}

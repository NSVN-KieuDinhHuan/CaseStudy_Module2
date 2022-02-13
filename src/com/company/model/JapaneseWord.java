package com.company.model;

import java.io.Serializable;

public class JapaneseWord implements Serializable {
    private String kanji;
    private String hiragana;
    private String pronunciation;

    public JapaneseWord(String kanji, String hiragana, String pronunciation) {
        this.kanji = kanji;
        this.hiragana = hiragana;
        this.pronunciation = pronunciation;
    }

    public JapaneseWord() {
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }
    @Override
    public String toString() {
        return  kanji + '\n' +
                hiragana + '\n' +
                pronunciation + '\n';
    }
}

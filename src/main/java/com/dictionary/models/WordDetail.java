package com.dictionary.models;


public class WordDetail implements DictionaryEntry {
    private String word;
    private String meaning;
    private String[] synonyms;
    private String[] antonyms;

    public WordDetail(String word, String meaning, String[] synonyms, String[] antonyms) {
        this.word = word;
        this.meaning = meaning;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
    }

    @Override
    public String getWord() { return word; }

    @Override
    public String getMeaning() { return meaning; }

    @Override
    public String[] getSynonyms() { return synonyms; }

    @Override
    public String[] getAntonyms() { return antonyms; }

    @Override
    public String toString() {
        return "Word: " + word + "\n" +
               "Meaning: " + meaning + "\n" +
               "Synonyms: " + String.join(", ", synonyms) + "\n" +
               "Antonyms: " + String.join(", ", antonyms);
    }
}

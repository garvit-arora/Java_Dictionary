package com.dictionary.data;

import com.dictionary.models.DictionaryEntry;
import com.dictionary.models.WordDetail;
import com.dictionary.models.WordNotFoundException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class DictionaryStore {
    private Hashtable<String, DictionaryEntry> wordMap;

    public DictionaryStore() {
        wordMap = new Hashtable<>();
        loadData();
    }

    private void loadData() {
        try (InputStream is = getClass().getResourceAsStream("/dictionary.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
              
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                if (tokenizer.countTokens() >= 4) {
                    String word = tokenizer.nextToken().trim();
                    String meaning = tokenizer.nextToken().trim();
                    String synStr = tokenizer.nextToken().trim();
                    String antStr = tokenizer.nextToken().trim();

                    String[] synonyms = synStr.split(", ");
                    String[] antonyms = antStr.split(", ");

                    wordMap.put(word.toLowerCase(), new WordDetail(word, meaning, synonyms, antonyms));
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
    }

    public DictionaryEntry searchWord(String word) throws WordNotFoundException {
        if (word == null || word.isEmpty()) {
            throw new WordNotFoundException("Search query cannot be empty.");
        }
        
        DictionaryEntry entry = wordMap.get(word.toLowerCase());
        if (entry == null) {
            throw new WordNotFoundException("Word '" + word + "' not found in dictionary.");
        }
        return entry;
    }
}

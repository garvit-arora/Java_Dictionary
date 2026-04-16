package com.dictionary.models;

import java.io.Serializable;

/**
 * Interface representing a dictionary entry as per OOP principles in syllabus.
 */
public interface DictionaryEntry extends Serializable {
    String getWord();
    String getMeaning();
    String[] getSynonyms();
    String[] getAntonyms();
}

package com.dictionary.models;

import java.io.Serializable;


public interface DictionaryEntry extends Serializable {
    String getWord();
    String getMeaning();
    String[] getSynonyms();
    String[] getAntonyms();
}

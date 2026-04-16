package com.dictionary.main;

import com.dictionary.gui.DictionaryUI;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            DictionaryUI ui = new DictionaryUI();
            ui.setVisible(true);
        });
    }
}

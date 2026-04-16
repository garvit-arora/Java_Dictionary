package com.dictionary.main;

import com.dictionary.gui.DictionaryUI;
import javax.swing.SwingUtilities;

/**
 * Entry point for the Dictionary Application.
 * 
 * This project covers the following syllabus units:
 * - UNIT I: Compilation/Execution (Application structure)
 * - UNIT II: Fundamentals, Classes, Interfaces, Inheritance, Exception Handling, String processing.
 * - UNIT III: Multi-threading (Searching thread), AWT Components, Layout Managers, Event model.
 * - UNIT IV: I/O Streams (Loading dictionary from file), Collection API (Hashtable usage).
 */
public class Main {
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (Unit III)
        SwingUtilities.invokeLater(() -> {
            DictionaryUI ui = new DictionaryUI();
            ui.setVisible(true);
        });
    }
}

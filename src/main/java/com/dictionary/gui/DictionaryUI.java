package com.dictionary.gui;

import com.dictionary.data.DictionaryStore;
import com.dictionary.models.DictionaryEntry;
import com.dictionary.models.WordNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class DictionaryUI extends JFrame {
    private JTextField searchField;
    private JTextArea resultArea;
    private JButton searchButton;
    private DictionaryStore store;

    public DictionaryUI() {
        store = new DictionaryStore();
        initUI();
    }

    private void initUI() {
        setTitle("Modern Java Dictionary");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 247, 250));
        JLabel headerLabel = new JLabel("Dictionary Search", JLabel.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        headerLabel.setForeground(new Color(44, 62, 80));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setOpaque(false);

        searchField = new JTextField(20);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        searchField.setPreferredSize(new Dimension(250, 35));
        
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchButton.setBackground(new Color(52, 152, 219));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setPreferredSize(new Dimension(100, 35));

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)));

        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        centerPanel.setOpaque(false);
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performSearch();
                }
            }
        });

        add(mainPanel);
    }

    private void performSearch() {
        String word = searchField.getText().trim();
        resultArea.setText("Searching...");
        
        
        new Thread(() -> {
            try {
                
                Thread.sleep(300);
                DictionaryEntry entry = store.searchWord(word);
                
                
                SwingUtilities.invokeLater(() -> {
                    displayResult(entry);
                });
            } catch (WordNotFoundException ex) {
                SwingUtilities.invokeLater(() -> {
                    resultArea.setForeground(Color.RED);
                    resultArea.setText("Error: " + ex.getMessage());
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void displayResult(DictionaryEntry entry) {
        resultArea.setForeground(new Color(44, 62, 80));
        StringBuilder sb = new StringBuilder();
        sb.append("WORD: ").append(entry.getWord().toUpperCase()).append("\n\n");
        sb.append("MEANING:\n").append(entry.getMeaning()).append("\n\n");
        
        sb.append("SYNONYMS:\n");
        for (String s : entry.getSynonyms()) sb.append("- ").append(s).append("\n");
        sb.append("\n");

        sb.append("ANTONYMS:\n");
        for (String a : entry.getAntonyms()) sb.append("- ").append(a).append("\n");

        resultArea.setText(sb.toString());
        resultArea.setCaretPosition(0);
    }
}

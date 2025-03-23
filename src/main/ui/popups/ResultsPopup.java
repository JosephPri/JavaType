package ui.popups;

import javax.swing.*;

import model.TypingTest;

import java.awt.*;

// represents a popup window that displays your test results
public class ResultsPopup extends Popup {

    // REQUIRES: test is not null
    // EFFECTS: displays a pop up window with the results of given test
    public ResultsPopup(TypingTest test) {
        super("Test Results", 200, 150);
        JPanel panel = new JPanel();

        JLabel difficulty = new JLabel("Difficulty: " + test.getDifficulty());
        JLabel duration = new JLabel("Duration: " + test.getDuration() + " seconds");
        JLabel content = new JLabel("Content: " + test.getContentType());
        JLabel seed = new JLabel("Seed: " + test.getSeed());
        JLabel wpm = new JLabel("WPM: " + test.getWPM()); 
        JLabel accuracy = new JLabel("Accuracy: " + test.getAccuracy() + "%");
        panel.add(difficulty);
        panel.add(duration);
        panel.add(content);
        if (test.getContentType().equals("random words")) {
            panel.add(seed);
        }
        panel.add(wpm);
        panel.add(accuracy);
        add(panel);
        setVisible(true);
    }
}

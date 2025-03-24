package ui.tabs;

import ui.SwingTypingTestApp;
import model.TypingTest;
import model.TypingTestHistory;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

// represents the history tab of the gui application 
public class HistoryTab extends Tab {
    private JLabel averageAccuracy;         // overall test avverage accuracy
    private JLabel averageWPM;              // overall test average words per minute
    private TypingTestHistory history;      // copy of UI's typing test history 
    private JPanel testResultsPanel;        // separate gui panel for test results

    // MODIFIES: this
    // EFFECTS: creates a history tab with overall stats listed and minimum wpm and accuracy filter
    public HistoryTab(SwingTypingTestApp controller) {
        super(controller);
        history = getController().getHistory();
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(placeOverallStats());
        topPanel.add(placeFilters());

        add(topPanel);

        testResultsPanel = new JPanel();
        add(testResultsPanel);
    }
    
    // MODIFIES: this
    // EFFECTS: places overall average wpm and accuracy stats
    private JPanel placeOverallStats() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(550, 60));
        panel.setBorder(BorderFactory.createTitledBorder("Overall Stats"));

        averageWPM = new JLabel("WPM: " + history.getAverageWPM());
        averageAccuracy = new JLabel("Accuracy: " + history.getAverageAccuracy() + "%");

        panel.add(averageWPM);
        panel.add(averageAccuracy);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: places filter fields
    private JPanel placeFilters() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Filters"));

        JTextField minWpmField = new JTextField(3);
        JTextField minAccuracyField = new JTextField(3);
        JButton filterButton = new JButton("Apply");

        filterButton.addActionListener(e -> loadTestLabels(applyFilters(minWpmField, minAccuracyField)));

        panel.add(new JLabel("Minimum WPM:"));
        panel.add(minWpmField);
        panel.add(new JLabel("Minimum Accuracy:"));
        panel.add(minAccuracyField);
        panel.add(filterButton);

        return panel;
    }

    // EFFECTS: returns list of tests that meet both minimum requirements
    private List<TypingTest> applyFilters(JTextField minWPM, JTextField minAccuracy) {
        int wpm = 0;
        if (!minWPM.getText().trim().isEmpty()) {
            wpm = Integer.parseInt(minWPM.getText().trim());
        }

        int accuracy = 0;
        if (!minAccuracy.getText().trim().isEmpty()) {
            accuracy = Integer.parseInt(minAccuracy.getText().trim());
        }

        List<TypingTest> accuracies = history.getTestsAccuracy(accuracy);
        List<TypingTest> wpms = history.getTestsWPM(wpm);

        List<TypingTest> shared = new ArrayList<>();
        for (TypingTest test : accuracies) {
            if (wpms.contains(test)) {
                shared.add(test);
            }
        }
        return shared;
    }

    // MODIFIES: this
    // EFFECTS: places all of the tests in a given list
    private void loadTestLabels(List<TypingTest> tests) {
        testResultsPanel.removeAll();

        int counter = 1;
        for (TypingTest test : tests) {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.setBorder(BorderFactory.createTitledBorder("Test #" + counter));

            panel.add(new JLabel("Difficulty: " + test.getDifficulty()));
            panel.add(new JLabel("Duration: " + test.getDuration() + "s"));
            panel.add(new JLabel("Content: " + test.getContentType()));
            if ("random words".equals(test.getContentType())) {
                panel.add(new JLabel("Seed: " + test.getSeed()));
            }
            panel.add(new JLabel("WPM: " + test.getWPM()));
            panel.add(new JLabel("Accuracy: " + test.getAccuracy() + "%"));

            testResultsPanel.add(panel);
            counter++;
        }

        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: resets visuals for when new tests are added
    public void updateHistory() {
        history = getController().getHistory();
        averageWPM.setText("WPM: " + history.getAverageWPM());
        averageAccuracy.setText("Accuracy: " + history.getAverageAccuracy() + "%");

        testResultsPanel.removeAll();
        revalidate();
        repaint();
    }
}

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
        
    }

    // MODIFIES: this
    // EFFECTS: places overall average wpm and accuracy stats
    private JPanel placeOverallStats() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: places filter fields
    private JPanel placeFilters() {

        return null;
    }

    // EFFECTS: returns list of tests that meet both minimum requirements
    private List<TypingTest> applyFilters(JTextField minWPM, JTextField minAccuracy) {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: places all of the tests in a given list
    private void loadTestLabels(List<TypingTest> tests) {
  
    }

    // MODIFIES: this
    // EFFECTS: resets visuals for when new tests are added
    public void updateHistory() {

    }
}

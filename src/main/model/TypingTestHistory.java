package model;

import model.TypingTest;

import java.util.ArrayList;
import java.util.List;

// represents a history of typing tests
public class TypingTestHistory {
    List<TypingTest> history; // ArrayList to store typing test objects

    // MODIFIES: this
    // EFFECTS: instantiates an empty typing test history
    public TypingTestHistory() {
    }

    // MODIFIES: this
    // REQUIRES: test is not null
    // EFFECTS: adds a typing test to the history
    public void addTest(TypingTest test) {
    }

    // EFFECTS: returns the history of typing tests
    public ArrayList<TypingTest> getHistory() {
        return null; //stub
    }

    // EFFECTS: returns the overall average WPM of all tests in history;
    //          if history is empty, returns 0
    public int getAverageWPM() {
        return -1; //stub
    }

    // EFFECTS: returns the overall accuracy percentage of all tests in history;
    //          if history is empty, returns 0
    public int getAverageAccuracy() {
        return -1; //stub
    }
}

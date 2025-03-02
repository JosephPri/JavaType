package model;

import java.util.ArrayList;
import java.util.List;

// represents a history of typing tests
public class TypingTestHistory {
    List<TypingTest> history; // ArrayList to store typing test objects

    // MODIFIES: this
    // EFFECTS: instantiates an empty typing test history
    public TypingTestHistory() {
        history = new ArrayList<TypingTest>();
    }

    // MODIFIES: this
    // REQUIRES: test is not null
    // EFFECTS: adds a typing test to the history
    public void addTest(TypingTest test) {
        history.add(test);
    }

    // EFFECTS: returns the history of typing tests
    public List<TypingTest> getHistory() {
        return history;
    }

    // EFFECTS: returns the overall average WPM of all tests in history;
    //          if history is empty, returns 0
    public int getAverageWPM() {
        if (history.isEmpty()) {
            return 0;
        }

        int totalWPM = 0;
        for (TypingTest test : history) {
            totalWPM += test.getWPM();
        }

        return totalWPM / history.size();
    }

    // EFFECTS: returns the overall accuracy percentage of all tests in history;
    //          if history is empty, returns 0
    public int getAverageAccuracy() {
        if (history.isEmpty()) {
            return 0;
        }

        int totalAccuracy = 0;
        for (TypingTest test : history) {
            totalAccuracy += test.getAccuracy();
        }

        return totalAccuracy / history.size();
    }

    @Override
    // EFFECTS: returns history as a JSONObject
    public JSONObject toJson() {
    }

    // EFFECTS: returns tests in this history as a JSON array
    private JSONArray testsToJson() {

    }
}

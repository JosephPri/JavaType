package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// represents a history of typing tests
public class TypingTestHistory implements Writable {
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
        EventLog.getInstance().logEvent(new Event("Typing test added to history"));
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

    // EFFECTS: returns list of tests with minimum specified words per minute
    public List<TypingTest> getTestsWPM(int min) {
        List<TypingTest> newList = new ArrayList<TypingTest>();
        for (TypingTest test : history) {
            if (test.getWPM() >= min) {
                newList.add(test);
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered tests for a minimum WPM of " + min));
        return newList;
    }

    // EFFECTS: returns list of tests with minimum specified accuracy
    public List<TypingTest> getTestsAccuracy(int min) {
        List<TypingTest> newList = new ArrayList<TypingTest>();
        for (TypingTest test : history) {
            if (test.getAccuracy() >= min) {
                newList.add(test);
            }
        }
        EventLog.getInstance().logEvent(new Event("Filtered tests for a minimum accuracy of " + min));
        return newList;
    }

    @Override
    // EFFECTS: returns history as a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("history", testsToJson());
        return json;
    }

    // EFFECTS: returns tests in this history as a JSON array
    private JSONArray testsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (TypingTest test : history) {
            jsonArray.put(test.toJson());
        }
        return jsonArray;
    }
}
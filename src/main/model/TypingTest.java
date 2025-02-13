package model;

// Represents a typing test
public class TypingTest {
    private String difficulty;
    private int duration;
    private String textContent;
    private String userInput;

    private int wordsPerMinute;
    private int accuracy;

    // EFFECTS: instantiates a typing test with a difficulty, duration, text content, and 
    //          user text input to calculate words per minute (WPM) and accuracy.
    public TypingTest(String difficulty, int duration, String textContent, String userInput) {
    }

    public String getDifficulty() {
        return ""; //stub
    }

    // MODIFIES: this
    // EFFECTS: updates difficulty and recalculates wpm and accuracy
    public void setDifficulty(String difficulty) {
    }

    public int getDuration() {
        return -1; //stub
    }

    // REQUIRES: duration > 0
    // MODIFIES: this
    // EFFECTS: updates duration and recalculates wpm and accuracy
    public void setDuration(int duration) {
    }

    public String getTextContent() {
        return ""; //stub
    }

    // MODIFIES: this
    // EFFECTS: updates text content and recalculates wpm and accuracy
    public void setTextContent(String textContent) {
    }

    public String getUserInput() {
        return ""; //stub
    }

    // MODIFIES: this
    // EFFECTS: updates user input and recalculates wpm and accuracy
    public void setUserInput(String userInput) {
    }

    public int getWPM() {
        return -1; //stub
    }

    public int getAccuracy() {
        return -1; //stub
    }

    // MODIFIES: this
    // EFFECTS: calculates user's words per minute and accuracy;
    //          if difficulty == "hard" then words are case-sensitive and incorrect case will detract from WPM and 
    //          accuracy
    public void updateResults() {
    }

    // MODIFIES: this
    // EFFECTS: calculates user's words per minute;
    //          if difficulty == "hard" then words are case-sensitive and incorrect case will detract from WPM
    public int calculateWPM() {
        return -1; //stub
    }

    // MODIFIES: this
    // EFFECTS: calculates user's per word accuracy;
    //          if difficulty == "hard" then words are case-sensitive and incorrect case will detract from accuracy
    public int calculateAccuracy() {
        return -1; //stub
    }
}

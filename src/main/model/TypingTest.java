package model;

// Represents a typing test having a difficulty, duration, test content, user input
public class TypingTest {
    private String difficulty;                      // the difficulty of the test ("standard"/"hard")
    private int duration;                           // how long the test will last in seconds
    private String testContent;                     // the text the user needs to type
    private String userInput;                       // the text input typed by the user

    private int wordsPerMinute;                     // calculated average amount of words typed per minute
    private int accuracy;                           // calculated accuracy percentage per word

    private String standardText;                    // modified given testContent without capitalization or punctuation
    private String hardText;                        // unmodified given testContent

    private static final String SYLLABUS = "";      // preloaded CPSC 210 syllabus for test content
    private static final String RANDOM_WORDS = "";  // preloaded list of random words for test content

    // REQUIRES: duration > 0, testContent != null, (difficulty.equals("standard") | difficulty.equals("hard"))
    // EFFECTS: instantiates a typing test with a given difficulty, duration, test content and user input
    //          as well as computed hard and standard test content, and uncalculated wpm and accuracy;
    //          if testContent.equals("cpsc210 syllabus"), testContent will be the syllabus;
    //          else if testContent.equals("random words"), testContent will be a list of random words;
    //          if difficulty.equals("standard") testContent will be made all lowercase and punctuation will be removed
    public TypingTest(String difficulty, int duration, String testContent, String userInput) {
    }

    public String getDifficulty() {
        return null; //stub
    }

    // REQUIRES: difficulty.equals("standard") | difficulty.equals("hard")
    // MODIFIES: this
    // EFFECTS: updates difficulty, testContent and resets accuracy
    public void setDifficulty(String difficulty) {
    }

    public int getDuration() {
        return -1; //stub
    }

    // REQUIRES: duration > 0
    // MODIFIES: this
    // EFFECTS: updates duration and resets wpm
    public void setDuration(int duration) {
    }

    public String getTestContent() {
        return null; //stub
    }

    // MODIFIES: this
    // EFFECTS: updates test content and resets accuracy
    public void setTestContent(String testContent) {
    }

    public String getUserInput() {
        return null; //stub
    }

    // MODIFIES: this
    // EFFECTS: updates user input and resets wpm and accuracy
    public void setUserInput(String userInput) {
    }

    // REQUIRES: userInput != null
    // MODIFIES: this
    // EFFECTS: returns wpm;
    //          if wordsPerMinute == -1 (has not been calculated) calculates wpm
    public int getWPM() {
        return -1; //stub
    }

    // REQUIRES: userInput != null
    // MODIFIES: this
    // EFFECTS: returns accuracy;
    //          if accuracy == -1 (has not been calculated) calculates accuracy
    public int getAccuracy() {
        return -1; //stub
    }

    // MODIFIES: this
    // EFFECTS: calculates user's words per minute;
    private int calculateWPM() {
        return -1; //stub
    }

    // MODIFIES: this
    // EFFECTS: calculates user's per word accuracy;
    private int calculateAccuracy() {
        return -1; //stub
    }

    // REQUIRES: input is not an empty list
    // EFFECTS: returns input list containing words separated by space with randomized order
    private static String randomize(String input) {
        return null; //stub
    }
}
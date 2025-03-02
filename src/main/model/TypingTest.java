package model;

import java.util.Random;

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

    private String contentType;                     // type of test content ("random words"/"cpsc210 syllabus"/"custom")

    private int seed;                            // randomly generated seed for generating random word list

    private static final String SHORTENED_SYLLABUS = "Design, development, and analysis of robust software "
                                                 + "components. Topics such as software design, computational "
                                                 + "models, data structures, debugging, and testing. "
                                                 + "Prerequisite: One of CPSC 107, CPSC 110. "
                                                 + "Lectures: There are 3 hours of lecture each week. The course "
                                                 + "is “flipped” in that there are instructional videos that "
                                                 + "students are required to watch before each lecture. "
                                                 + "Assessment of pre/post-class learning will take place using "
                                                 + "online “lecture tickets”. In general, each lecture hour covers "
                                                 + "a single topic (represented by a module on edX). "
                                                 + "Labs: There will be several skill-building activities that "
                                                 + "you do in lab and on your own time over the course of the "
                                                 + "term. These must be done individually. You should be registered "
                                                 + "for one 2-hour lab section each week. Attendance at the lab "
                                                 + "section in which you are registered is required. "
                                                 + "During the lab, you can get help on assigned lab activities "
                                                 + "and the term project (see below), as well as general assistance "
                                                 + "from the TAs on any aspect of the course. In addition, you "
                                                 + "will be asked to demonstrate and will be evaluated on work "
                                                 + "that you submit for labs and the term project.";
                                                 // preloaded shortened CPSC 210 syllabus for test content

    private static final String RANDOM_WORDS = "Absorbing Fine Flung Transfer Natural Over The Home Hour Pour "
                                                + "Resemble Decorate Eatable Dazzling Chance Tightfisted Treat "
                                                + "Noiseless Arise Property Guiltless Agreeable Fowl Innocent Rat "
                                                + "Lift Lean Leg Famous Quirky Curly Reaction Three Terrify Dirt "
                                                + "Inculcate Daily Qualify Wax Governor Zinc Stink Ceaseless Faded "
                                                + "Dark Cabbage Stem Greet Hurt Neglect Bite Impair Corrode Blushing "
                                                + "Tangy Sore Nice Meaty Smoggy Misty Recast Touch Plucky Manage "
                                                + "Stray Youthful Bless Erratic Complete Choke Valuable Railway Tray "
                                                + "Square Crowd Reduce Fall Comfort Chin Superficial Organize Window "
                                                + "First Thrust Tree Move Common Late Messy Try Endorse Separate " 
                                                + "Confine Holistic Grade Level Sick Fretful Crazy Popcorn Clean " 
                                                + "Enlarge Last Joke Infringe Temporary Momentous Proceed Torpid "
                                                + "Conserve Accessible Convince Rabid Befallen Envious "
                                                + "Sound Discreet Unique Bubble Output Field Deserted Bad Story Burn " 
                                                + "Desire Tin Ashamed Forgetful Bid Sip Drill Eager Cry Pumped Bust "
                                                + "Defective Behold Relax Exchange Tame Mould Educate Cheer Route "
                                                + "Join Dispensable"; // preloaded list of random words for test content

// preloaded list of random words for test content
    // REQUIRES: duration > 0, testContent != null, (difficulty.equals("standard") | difficulty.equals("hard"))
    // MODIFIES: this
    // EFFECTS: instantiates a typing test with a given difficulty, duration, test content and user input
    //          as well as computed hard and standard test content, and uncalculated wpm and accuracy;
    //          if testContent.equals("cpsc210 syllabus"), testContent will be the syllabus;
    //          else if testContent.equals("random words"), testContent will be a list of random words;
    //          if difficulty.equals("standard") testContent will be made all lowercase and punctuation will be removed
    public TypingTest(String difficulty, int duration, String testContent, String userInput) {
        seed = (int) (Math.random() * 10000);
        setDifficulty(difficulty);
        setDuration(duration);
        setTestContent(testContent);
        setUserInput(userInput);
    }

    public String getDifficulty() {
        return difficulty;
    }

    // REQUIRES: difficulty.equals("standard") | difficulty.equals("hard")
    // MODIFIES: this
    // EFFECTS: updates difficulty, testContent and resets accuracy
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        if (this.difficulty.equals("standard")) {
            testContent = standardText;
        } else {
            testContent = hardText;
        }
        accuracy = -1;
    }

    public int getDuration() {
        return duration;
    }

    // REQUIRES: duration > 0
    // MODIFIES: this
    // EFFECTS: updates duration and resets wpm
    public void setDuration(int duration) {
        this.duration = duration;
        wordsPerMinute = -1;
    }

    public String getTestContent() {
        return testContent;
    }

    // MODIFIES: this
    // EFFECTS: updates test content and resets accuracy
    public void setTestContent(String testContent) {
        if (testContent.equals("cpsc210 syllabus")) {
            contentType = "cpsc210 syllabus";
            hardText = SHORTENED_SYLLABUS;
            standardText = SHORTENED_SYLLABUS.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
        } else if (testContent.equals("random words")) {
            contentType = "random words";
            String randomized = randomize(RANDOM_WORDS, seed);
            hardText = randomized;
            standardText = randomized.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
        } else {
            contentType = "custom";
            hardText = testContent;
            standardText = testContent.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
        }

        if (difficulty.equals("standard")) {
            this.testContent = standardText;
        } else {
            this.testContent = hardText;
        }
        accuracy = -1;
    }

    public String getUserInput() {
        return userInput;
    }

    // MODIFIES: this
    // EFFECTS: updates user input and resets wpm and accuracy;
    public void setUserInput(String userInput) {
        this.userInput = userInput;
        wordsPerMinute = -1;
        accuracy = -1;
    }

    // EFFECTS: returns type of test content
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getSeed() {
        return seed;
    }

    // REQUIRES: seed < 10000
    // EFFECTS: sets the seed and recalculates the random words if that is the content type
    public void setSeed(int seed) {
        this.seed = seed;
        if (contentType.equals("random words")) {
            String randomized = randomize(RANDOM_WORDS, seed);
            hardText = randomized;
            standardText = randomized.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
        }
        if (difficulty.equals("standard")) {
            this.testContent = standardText;
        } else {
            this.testContent = hardText;
        }
        accuracy = -1;
    }

    public String getHardText() {
        return hardText;
    }

    // REQUIRES: userInput != null
    // MODIFIES: this
    // EFFECTS: returns wpm;
    //          if wordsPerMinute == -1 (has not been calculated) calculates wpm
    public int getWPM() {
        if (wordsPerMinute == -1) {
            wordsPerMinute = calculateWPM();
        }
        return wordsPerMinute;
    }

    // REQUIRES: userInput != null
    // MODIFIES: this
    // EFFECTS: returns accuracy;
    //          if accuracy == -1 (has not been calculated) calculates accuracy
    public int getAccuracy() {
        if (accuracy == -1) {
            accuracy = calculateAccuracy();
        }
        return accuracy;
    }

    // MODIFIES: this
    // EFFECTS: calculates and returns user's words per minute;
    private int calculateWPM() {
        if (userInput == null || userInput.trim().isEmpty()) {
            return 0;
        }
        String[] words = userInput.trim().replaceAll("\\s+", " ").split("\\s+");
        return words.length * 60 / duration;
    }

    // MODIFIES: this
    // EFFECTS: calculates and returns user's per word accuracy;
    private int calculateAccuracy() {
        if (userInput == null) {
            return 0;
        }

        String trimmedUserInput = userInput.trim().replaceAll("\\s+", " "); // removes extra spaces
        String trimmedtestContent = testContent.trim().replaceAll("\\s+", " "); // removes extra spaces

        String[] userWords;
    
        if (difficulty.equals("standard")) {
            trimmedUserInput = trimmedUserInput.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().replaceAll("\\s+", " ");
        }
    
        userWords = trimmedUserInput.split("\\s+");

        String[] contentWords = trimmedtestContent.split("\\s+");

        int correctWords = 0;
        int totalValidWords = Math.min(userWords.length, contentWords.length);
        
        for (int i = 0; i < totalValidWords; i++) {
            if (userWords[i].equals(contentWords[i])) {
                correctWords++;
            }
        }

        return (correctWords * 100) / totalValidWords;
    }

    // REQUIRES: input is not an empty list
    // EFFECTS: returns input list containing words separated by space with randomized order using seed
    private static String randomize(String input, int seed) {
        String[] words = input.split("\\s");

        String newString = "";

        Random generator = new Random(seed);
        for (int i = 0; i < words.length; i++) {
            int randomNum = generator.nextInt(words.length);
            newString += words[randomNum] + " ";
        }

        return newString.trim();
    }

    // EFFECTS: returns test as a JSON object
    @Override
    public JSONObject toJson() {        
    }
}
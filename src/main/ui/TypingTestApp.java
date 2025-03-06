package ui;

import model.TypingTest;
import model.TypingTestHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Typing test application
public class TypingTestApp {
    private static final String JSON_STORE = "./data/typingTestHistory.json";   // default file storage location
    private TypingTestHistory history;                                          // list of previous typing tests
    private Scanner input;                                                      // general input given by user 
    private JsonWriter jsonWriter;                                              // object to write json files
    private JsonReader jsonReader;                                              // object to read json files

    // EFFECTS: runs the typing test application
    public TypingTestApp() throws FileNotFoundException {
        runTypingTestApp();
    }
    
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTypingTestApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
        
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("App closed");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("t")) {
            createNewTest();
        } else if (command.equals("h")) {
            openHistory();
        } else if (command.equals("l")) {
            loadHistory();
        } else if (command.equals("s")) {
            saveHistory();
        } else {
            System.out.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes objects
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        history = new TypingTestHistory();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tt -> take a new test");
        System.out.println("\th -> look at test history");
        System.out.println("\tl -> load test history from file");
        System.out.println("\ts -> save test history to file");
        System.out.println("\tq -> quit");
    }
    
    // MODIFIES: this
    // EFFECTS: lets user take a typing test
    private void createNewTest() {
        String difficulty = processCommand2String("Enter difficulty of test (standard/hard): ", "standard", "hard");

        int duration = processCommand1Int();
        
        System.out.print("Enter test content type (random words/cpsc210 syllabus/custom): ");
        String testContent = input.next().toLowerCase();
        while (!testContent.equals("random words") 
                && !testContent.equals("cpsc210 syllabus") 
                && !testContent.equals("custom")) {
            System.out.println("Selection not valid!");
            System.out.print("Enter test content type (random words/cpsc210 syllabus/custom): ");
            testContent = input.next();
        }

        if (testContent.equals("custom")) {
            System.out.print("Enter custom text content: ");
            testContent = input.next();
        }
        TypingTest test = new TypingTest(difficulty, duration, testContent, null);

        takeTest(test);

        promptRetake(test);
    }

    // EFFECTS: prompts user on whether they want to retake the same test and runs if so
    private void promptRetake(TypingTest test) {
        String retake = processCommand2String("Would you like to retake the same test? (yes/no): ", "yes", "no");
        if (retake.equals("yes")) {
            TypingTest duplicateTest = new TypingTest(test.getDifficulty(), test.getDuration(), test.getContentType(), 
                                            null);
            if (test.getContentType().equals("custom")) {
                duplicateTest.setTestContent(test.getHardText());
            }
            duplicateTest.setSeed(test.getSeed());    
            takeTest(duplicateTest);
        }
    }

    // EFFECTS: returns user command once the command is a valid option
    private String processCommand2String(String prompt, String option1, String option2) {
        System.out.print(prompt);
        String object = input.next().toLowerCase();
        while (!object.equals(option1) && !object.equals(option2)) {
            System.out.println("Selection not valid!");
            System.out.print(prompt);
            object = input.next().toLowerCase();
        }
        return object;
    }

    // EFFECTS: returns user duration input once the input is greater than 0 and a number
    private int processCommand1Int() {
        int duration;
        System.out.print("Enter test duration (seconds): ");
        while (true) {
            if (input.hasNextInt()) {
                duration = input.nextInt();
                if (duration > 0) {
                    break;
                }
            } else {
                input.next();
            }
            System.out.println("Selection not valid!");
            System.out.print("Enter test duration (seconds): ");
        }
        return duration;
    }

    // MODIFIES: this
    // EFFECTS: allows user to view history
    private void openHistory() {
        if (history.getHistory().size() == 0) {
            System.out.println("You have not yet taken any tests!");
            return;
        }

        System.out.println("Overall Stats:");
        System.out.println("Average WPM: " + history.getAverageWPM());
        System.out.println("Average Accuracy: " + history.getAverageAccuracy() + "\n");

        List<TypingTest> historyList = history.getHistory();
        for (int i = 0; i < historyList.size(); i++) {
            TypingTest currentTest = historyList.get(i);
            System.out.println("Test #" + (i + 1) + " Statistics:");
            printTestResults(currentTest);
        }
    }

    // MODIFIES: test
    // EFFECTS: runs the given test and saves it to the history
    private void takeTest(TypingTest test) {
        System.out.println("Press ENTER when you are ready for the test to start.");
        input.next();
        
        System.out.println(test.getTestContent() + "\n");

        String userInput = "";
        Thread timerThread = new Thread(() -> {
            startCountdown(test.getDuration());
        });
        timerThread.start();

        while (timerThread.isAlive()) {
            if (input.hasNextLine()) {
                userInput = input.nextLine();
            }
        }

        test.setUserInput(userInput);

        System.out.println("Here are your results!");
        printTestResults(test);

        history.addTest(test);
    }

    // EFFECTS: starts the countdown timer
    private void startCountdown(int duration) {
        for (int i = duration; i >= 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("\n\nTime's up! Press ENTER to see your results.");
    }

    // EFFECTS: saves history to file
    private void loadHistory() {
        try {
            history = jsonReader.read();
            System.out.println("Loaded typing test history from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads history from file
    private void saveHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(history);
            jsonWriter.close();
            System.out.println("Saved typing test history to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints difficulty, duration, content, wpm, accuracy and seed if content type is random words
    private void printTestResults(TypingTest test) {
        System.out.println("Difficulty: " + test.getDifficulty());
        System.out.println("Duration: " + test.getDuration() + " seconds");
        System.out.println("Content: " + test.getContentType());
        if (test.getContentType().equals("random words")) {
            System.out.println("Seed: " + test.getSeed());
        }
        System.out.println("WPM: " + test.getWPM()); 
        System.out.println("Accuracy: " + test.getAccuracy() + "%\n");
    }
}

package ui;

import model.TypingTest;
import model.TypingTestHistory;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.List;

// Typing test application
public class TypingTestApp {
    private TypingTestHistory history;  // list of previous typing tests
    private Scanner input;              // general input given by user 

    // EFFECTS: runs the typing test application
    public TypingTestApp() {
    }
    
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTypingTestApp() {
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
    }

    // MODIFIES: this
    // EFFECTS: initializes objects
    private void init() {
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
    }
    
    // MODIFIES: this
    // EFFECTS: lets user take a typing test
    private void createNewTest() {
    }

    // EFFECTS: returns user command once the command is a valid option
    private String processCommand2String(String prompt, String option1, String option2) {
        return null; //stub
    }

    // EFFECTS: returns user duration input once the input is greater than 0 and a number
    private int processCommandDuration() {
        return -1; //stub
    }

    // MODIFIES: this
    // EFFECTS: allows user to view
    private void openHistory() {
    }

    // MODIFIES: test
    // EFFECTS: runs the given test and saves it to the history
    private void takeTest(TypingTest test) {
    }
}

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
    public TypingTestApp() throws InterruptedException {
    }
    
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTypingTestApp() throws InterruptedException {
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) throws InterruptedException {
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
    private void createNewTest() throws InterruptedException {
    }

    private String processCommand2String(String prompt, String option1, String option2) {
        return null; //stub
    }

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

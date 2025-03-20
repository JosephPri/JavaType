package ui;

import java.io.FileNotFoundException;

// Main class for console ui
public class ConsoleUIMain {
    // EFFECTS: runs console instance of typing test app
    public static void main(String[] args) throws Exception {
        try {
            new ConsoleTypingTestApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

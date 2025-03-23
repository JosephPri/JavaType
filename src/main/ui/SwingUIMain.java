package ui;

import java.io.FileNotFoundException;

// main class for swing gui
public class SwingUIMain {
    // EFFECTS: runs swing gui instance of typing test app
    public static void main(String[] args) throws Exception {
        try {
            new SwingTypingTestApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

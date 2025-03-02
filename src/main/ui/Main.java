package ui;

import java.io.FileNotFoundException;

public class Main {
    // runs console instance of typing test app
    public static void main(String[] args) throws Exception {
        try {
            new TypingTestApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

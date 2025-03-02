package persistence;

import model.TypingTest;
import model.TypingTestHistory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
    }

    // EFFECTS: reads typing test history from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TypingTestHistory read() throws IOException {
        return null; //stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null; //stub
    }

    // EFFECTS: parses typing test history from JSON object and returns it
    private TypingTestHistory parseHistory(JSONObject jsonObject) {
        return null; //stub
    }

    // MODIFIES: history
    // EFFECTS: parses typing tests from JSON object and adds them to typing test history
    private void addTests(TypingTestHistory history, JSONObject jsonObject) {
    }

    // MODIFIES: history
    // EFFECTS: parses typing test from JSON object and adds it to typing test history
    private void addTest(TypingTestHistory history, JSONObject jsonObject) {
    }
}

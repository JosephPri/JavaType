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
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads typing test history from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TypingTestHistory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHistory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses typing test history from JSON object and returns it
    private TypingTestHistory parseHistory(JSONObject jsonObject) {
        TypingTestHistory history = new TypingTestHistory();
        addTests(history, jsonObject);
        return history;
    }

    // MODIFIES: history
    // EFFECTS: parses typing tests from JSON object and adds them to typing test history
    private void addTests(TypingTestHistory history, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("history");
        for (Object json : jsonArray) {
            JSONObject nextTest = (JSONObject) json;
            addTest(history, nextTest);
        }
    }

    // MODIFIES: history
    // EFFECTS: parses typing test from JSON object and adds it to typing test history
    private void addTest(TypingTestHistory history, JSONObject jsonObject) {
        String difficulty = jsonObject.getString("difficulty");
        int duration = jsonObject.getInt("duration");
        String testContent = jsonObject.getString("testContent");
        String userInput = jsonObject.getString("userInput");
        int seed = jsonObject.getInt("seed");
        TypingTest test = new TypingTest(difficulty, duration, testContent, userInput);
        test.setSeed(seed);
        history.addTest(test);
    }
}

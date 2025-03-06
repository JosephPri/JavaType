package persistence;

import model.TypingTest;
import model.TypingTestHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TypingTestHistory history = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTypingTestHistory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTypingTestHistory.json");
        try {
            TypingTestHistory history = reader.read();
            assertEquals(0, history.getAverageAccuracy());
            assertEquals(0, history.getAverageWPM());
            assertEquals(new ArrayList<TypingTest>(), history.getHistory());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTypingTestHistory() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTypingTestHistory.json");
        try {
            TypingTestHistory history = reader.read();
            List<TypingTest> historyList = history.getHistory();
            checkTest("standard", 60, "random words", "blah blah", 50, historyList.get(0));
            checkTest("hard", 45, "cpsc210 syllabus", "Design, development", 30, historyList.get(1));
            checkTest("standard", 30, "This is a custom test.", "this is a", 20, historyList.get(2));
            assertEquals(50, history.getAverageAccuracy());
            assertEquals(3, history.getAverageWPM());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
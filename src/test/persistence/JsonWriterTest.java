package test.persistence;

import model.TypingTest;
import model.TypingTestHistory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            TypingTestHistory history = new TypingTestHistory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTypingTestHistory() {
        try {
            TypingTestHistory history = new TypingTestHistory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTypingTestHistory.json");
            writer.open();
            writer.write(history);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTypingTestHistory.json");
            history = reader.read();
            assertEquals(0, history.getAverageAccuracy());
            assertEquals(0, history.getAverageWPM());
            assertEquals(new ArrayList<TypingTest>(), history.getHistory());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTypingTestHistory() {
        try {
            TypingTestHistory history = new TypingTestHistory();
            TypingTest test1 = new TypingTest("standard", 60, "random words", "blah blah");
            test1.setSeed(50);
            history.addTest(test1);
            TypingTest test2 = new TypingTest("hard", 45, "cpsc210 syllabus", "Design, development");
            test2.setSeed(30);
            history.addTest(test2);
            TypingTest test3 = new TypingTest("standard", 30, "This is a custom test.", "this is a");
            test3.setSeed(20);
            history.addTest(test3);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTypingTestHistory.json");
            writer.open();
            writer.write(history);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTypingTestHistory.json");
            history = reader.read();
            assertEquals(33, history.getAverageAccuracy());
            assertEquals(3, history.getAverageWPM());
            assertEquals(3, history.getHistory().size());
            List<TypingTest> historyList = history.getHistory();
            System.out.println(historyList.get(0).getContentType());
            checkTest("standard", 60, "random words", "blah blah", 50, historyList.get(0));
            checkTest("hard", 45, "cpsc210 syllabus", "Design, development", 30, historyList.get(1));
            checkTest("standard", 30, "This is a custom test.", "this is a", 20, historyList.get(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
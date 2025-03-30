package persistence;

import model.TypingTest;
import model.TypingTestHistory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTest(String difficulty, int duration, String testContent,
                               String userInput, int seed, TypingTest test) {
        assertEquals(difficulty, test.getDifficulty());
        assertEquals(duration, test.getDuration());
        if (testContent.equals("cpsc210 syllabus") | testContent.equals("random words")) {
            assertEquals(testContent, test.getContentType());
        } else {
            assertEquals(testContent, test.getHardText());
        }
        assertEquals(userInput, test.getUserInput());
        assertEquals(seed, test.getSeed());
    }
}
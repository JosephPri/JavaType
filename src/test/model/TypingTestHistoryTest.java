package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TypingTestHistoryTest {
    private TypingTest standardTypingTest1;
    private TypingTest standardTypingTest2;
    private TypingTest standardTypingTest3;

    private TypingTest hardTypingTest1;
    private TypingTest hardTypingTest2;
    private TypingTest hardTypingTest3;

    private TypingTestHistory testHistory;

    private List<TypingTest> testList;

    @BeforeEach
    void runBefore() {
        standardTypingTest1 = new TypingTest("standard", 60, "This is a test.", "this is a test");
        standardTypingTest2 = new TypingTest("standard", 45, "This is a test.", "thies is a test");
        standardTypingTest3 = new TypingTest("standard", 120, "This is a test.", "this is an testt");

        hardTypingTest1 = new TypingTest("hard", 60, "This is a test.", "This is a test.");
        hardTypingTest2 = new TypingTest("hard", 45, "This is a test.", "this is a test");
        hardTypingTest3 = new TypingTest("hard", 120, "This is a test.", "this is an test");

        testHistory = new TypingTestHistory();
        testList = new ArrayList<TypingTest>();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testHistory.getAverageAccuracy());
        assertEquals(0, testHistory.getAverageWPM());
        assertEquals(testList, testHistory.getHistory());
    }

    @Test
    void testAddTest() {
        testHistory.addTest(standardTypingTest1);
        assertEquals(1, testHistory.getHistory().size());
        assertEquals(standardTypingTest1, testHistory.getHistory().get(0));

        testHistory.addTest(standardTypingTest2);
        assertEquals(2, testHistory.getHistory().size());

        testHistory.addTest(standardTypingTest2);
        assertEquals(3, testHistory.getHistory().size());
    }

    @Test
    void testGetHistory() {
        testHistory.addTest(standardTypingTest1);
        assertEquals(1, testHistory.getHistory().size());

        testList.add(standardTypingTest1);
        assertEquals(testList, testHistory.getHistory());
    }

    @Test
    void testGetAverageWPM() {
        testHistory.addTest(standardTypingTest1);
        testHistory.addTest(standardTypingTest2);
        testHistory.addTest(standardTypingTest3);
        testHistory.addTest(hardTypingTest1);
        testHistory.addTest(hardTypingTest2);
        testHistory.addTest(hardTypingTest3);

        assertEquals(3, testHistory.getAverageWPM());
    }

    @Test 
    void testGetAverageAccuracy() {
        testHistory.addTest(standardTypingTest1);
        testHistory.addTest(standardTypingTest2);
        testHistory.addTest(standardTypingTest3);
        testHistory.addTest(hardTypingTest1);
        testHistory.addTest(hardTypingTest2);
        testHistory.addTest(hardTypingTest3);

        assertEquals(66, testHistory.getAverageAccuracy());
    }

    @Test 
    void testGetTestsAccuracy() {
        assertEquals(testList, testHistory.getTestsAccuracy(100));

        testHistory.addTest(standardTypingTest2);
        testHistory.addTest(hardTypingTest2);
        assertEquals(testList, testHistory.getTestsAccuracy(100));

        testList.add(standardTypingTest2);
        testList.add(hardTypingTest2);

        assertEquals(testList, testHistory.getTestsAccuracy(50));
    }

    @Test
    void testGetTestsWPM() {
        assertEquals(testList, testHistory.getTestsWPM(2));

        testHistory.addTest(hardTypingTest1);
        testHistory.addTest(standardTypingTest1);
        assertEquals(testList, testHistory.getTestsWPM(100));

        testList.add(hardTypingTest1);
        testList.add(standardTypingTest1);
        assertEquals(testList, testHistory.getTestsWPM(2));
    }
}

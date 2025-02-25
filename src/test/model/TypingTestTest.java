package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TypingTestTest {
    private TypingTest standardTypingTest;
    private TypingTest hardTypingTest;

    @BeforeEach
    void runBefore() {
        standardTypingTest = new TypingTest("standard", 60, "This is a test.", "this is a test");
        hardTypingTest = new TypingTest("hard", 60, "This is a test.", "this is a test");
    }

    @Test
    void testConstructor() {
        assertEquals("standard", standardTypingTest.getDifficulty());
        assertEquals(60, standardTypingTest.getDuration());
        assertEquals("this is a test", standardTypingTest.getTestContent());
        assertEquals("this is a test", standardTypingTest.getUserInput());
        assertEquals(100, standardTypingTest.getAccuracy());
        assertEquals(4, standardTypingTest.getWPM());
        assertEquals("custom", standardTypingTest.getContentType());

        assertEquals("hard", hardTypingTest.getDifficulty());
        assertEquals(60, hardTypingTest.getDuration());
        assertEquals("This is a test.", hardTypingTest.getTestContent());
        assertEquals("this is a test", hardTypingTest.getUserInput());
        assertEquals(50, hardTypingTest.getAccuracy());
        assertEquals(4, hardTypingTest.getWPM());
        assertEquals("custom", hardTypingTest.getContentType());
    }

    @Test
    void testSetDifficulty() {
        standardTypingTest.setDifficulty("hard");

        assertEquals("hard", standardTypingTest.getDifficulty());
        assertEquals(60, standardTypingTest.getDuration());
        assertEquals("This is a test.", standardTypingTest.getTestContent());
        assertEquals("this is a test", standardTypingTest.getUserInput());
        assertEquals(50, standardTypingTest.getAccuracy());
        assertEquals(4, standardTypingTest.getWPM());

        hardTypingTest.setDifficulty("standard");

        assertEquals("standard", hardTypingTest.getDifficulty());
        assertEquals(60, hardTypingTest.getDuration());
        assertEquals("this is a test", hardTypingTest.getTestContent());
        assertEquals("this is a test", hardTypingTest.getUserInput());
        assertEquals(100, hardTypingTest.getAccuracy());
        assertEquals(4, hardTypingTest.getWPM());
    }

    @Test
    void testSetDuration() {
        standardTypingTest.setDuration(20);

        assertEquals("standard", standardTypingTest.getDifficulty());
        assertEquals(20, standardTypingTest.getDuration());
        assertEquals("this is a test", standardTypingTest.getTestContent());
        assertEquals("this is a test", standardTypingTest.getUserInput());
        assertEquals(100, standardTypingTest.getAccuracy());
        assertEquals(12, standardTypingTest.getWPM());

        hardTypingTest.setDuration(20);

        assertEquals("hard", hardTypingTest.getDifficulty());
        assertEquals(20, hardTypingTest.getDuration());
        assertEquals("This is a test.", hardTypingTest.getTestContent());
        assertEquals("this is a test", hardTypingTest.getUserInput());
        assertEquals(50, hardTypingTest.getAccuracy());
        assertEquals(12, hardTypingTest.getWPM());
    }

    @Test
    void testSetTextContent() {
        standardTypingTest.setTestContent("This is now a different test");

        assertEquals("standard", standardTypingTest.getDifficulty());
        assertEquals(60, standardTypingTest.getDuration());
        assertEquals("this is now a different test", standardTypingTest.getTestContent());
        assertEquals("this is a test", standardTypingTest.getUserInput());
        assertEquals(50, standardTypingTest.getAccuracy());
        assertEquals(4, standardTypingTest.getWPM());

        hardTypingTest.setTestContent("This is now a different test");

        assertEquals("hard", hardTypingTest.getDifficulty());
        assertEquals(60, hardTypingTest.getDuration());
        assertEquals("This is now a different test", hardTypingTest.getTestContent());
        assertEquals("this is a test", hardTypingTest.getUserInput());
        assertEquals(25, hardTypingTest.getAccuracy());
        assertEquals(4, hardTypingTest.getWPM());
    }

    @Test
    void testSetUserInput() {
        standardTypingTest.setUserInput("This may not be the words I'm looking for");

        assertEquals("standard", standardTypingTest.getDifficulty());
        assertEquals(60, standardTypingTest.getDuration());
        assertEquals("this is a test", standardTypingTest.getTestContent());
        assertEquals("This may not be the words I'm looking for", standardTypingTest.getUserInput());
        assertEquals(25, standardTypingTest.getAccuracy());
        assertEquals(9, standardTypingTest.getWPM());

        hardTypingTest.setUserInput("This may not be the words I'm looking for");

        assertEquals("hard", hardTypingTest.getDifficulty());
        assertEquals(60, hardTypingTest.getDuration());
        assertEquals("This is a test.", hardTypingTest.getTestContent());
        assertEquals("This may not be the words I'm looking for", hardTypingTest.getUserInput());
        assertEquals(25, hardTypingTest.getAccuracy());
        assertEquals(9, hardTypingTest.getWPM());
    }


    @Test
    void testSetContentType() {
        standardTypingTest.setContentType("random words");
        assertEquals("random words", standardTypingTest.getContentType());
    }
    
    @Test
    void testNoWordsTyped() {
        standardTypingTest.setUserInput("");

        assertEquals("standard", standardTypingTest.getDifficulty());
        assertEquals(60, standardTypingTest.getDuration());
        assertEquals("this is a test", standardTypingTest.getTestContent());
        assertEquals("", standardTypingTest.getUserInput());
        assertEquals(0, standardTypingTest.getAccuracy());
        assertEquals(0, standardTypingTest.getWPM());

        hardTypingTest.setUserInput("");

        assertEquals("hard", hardTypingTest.getDifficulty());
        assertEquals(60, hardTypingTest.getDuration());
        assertEquals("This is a test.", hardTypingTest.getTestContent());
        assertEquals("", hardTypingTest.getUserInput());
        assertEquals(0, hardTypingTest.getAccuracy());
        assertEquals(0, hardTypingTest.getWPM());

        standardTypingTest.setUserInput(null);

        assertEquals("standard", standardTypingTest.getDifficulty());
        assertEquals(60, standardTypingTest.getDuration());
        assertEquals("this is a test", standardTypingTest.getTestContent());
        assertEquals(null, standardTypingTest.getUserInput());
        assertEquals(0, standardTypingTest.getAccuracy());
        assertEquals(0, standardTypingTest.getWPM());

        hardTypingTest.setUserInput(null);

        assertEquals("hard", hardTypingTest.getDifficulty());
        assertEquals(60, hardTypingTest.getDuration());
        assertEquals("This is a test.", hardTypingTest.getTestContent());
        assertEquals(null, hardTypingTest.getUserInput());
        assertEquals(0, hardTypingTest.getAccuracy());
        assertEquals(0, hardTypingTest.getWPM());
    }

    @Test
    void testExtraSpacesTyped() {
        standardTypingTest.setUserInput(" this is a  test");

        assertEquals("standard", standardTypingTest.getDifficulty());
        assertEquals(60, standardTypingTest.getDuration());
        assertEquals("this is a test", standardTypingTest.getTestContent());
        assertEquals(" this is a  test", standardTypingTest.getUserInput());
        assertEquals(100, standardTypingTest.getAccuracy());
        assertEquals(4, standardTypingTest.getWPM());

        hardTypingTest.setUserInput(" this is a  test");

        assertEquals("hard", hardTypingTest.getDifficulty());
        assertEquals(60, hardTypingTest.getDuration());
        assertEquals("This is a test.", hardTypingTest.getTestContent());
        assertEquals(" this is a  test", hardTypingTest.getUserInput());
        assertEquals(50, hardTypingTest.getAccuracy());
        assertEquals(4, hardTypingTest.getWPM());
    }

    @Test
    void testEmptyOrWhitespaceInputAndContent() {
        standardTypingTest.setUserInput("     ");
        standardTypingTest.setTestContent("     ");
        assertEquals(100, standardTypingTest.getAccuracy());
        hardTypingTest.setUserInput("");
        hardTypingTest.setTestContent("");
        assertEquals(100, hardTypingTest.getAccuracy());
    }

    @Test
    void testOneofInputAndContentEmptyOneNot() {
        standardTypingTest.setUserInput("This is a test");
        standardTypingTest.setTestContent("");

        assertEquals(0, standardTypingTest.getAccuracy());

        hardTypingTest.setUserInput("");
        hardTypingTest.setTestContent("This is a test");

        assertEquals(0, hardTypingTest.getAccuracy());
    }

    @Test
    void testTestSetContentRandomWords() {
        standardTypingTest.setTestContent("random words");
        standardTypingTest.setUserInput("THESE WORDS ARE NOT IN THE LIST");

        assertEquals("standard", standardTypingTest.getDifficulty());
        assertEquals(60, standardTypingTest.getDuration());
        assertTrue(standardTypingTest.getTestContent().length() > 586);
        assertEquals("THESE WORDS ARE NOT IN THE LIST", standardTypingTest.getUserInput());
        assertEquals(0, standardTypingTest.getAccuracy());
        assertEquals(7, standardTypingTest.getWPM());

        hardTypingTest.setUserInput("THESE WORDS ARE NOT IN THE LIST");
        hardTypingTest.setTestContent("random words");

        assertEquals("hard", hardTypingTest.getDifficulty());
        assertEquals(60, hardTypingTest.getDuration());
        assertTrue(hardTypingTest.getTestContent().length() > 586);
        assertEquals("THESE WORDS ARE NOT IN THE LIST", hardTypingTest.getUserInput());
        assertEquals(0, hardTypingTest.getAccuracy());
        assertEquals(7, hardTypingTest.getWPM());
    }

    @Test
    void testTestSetContentSyllabus() {
        standardTypingTest.setTestContent("cpsc210 syllabus");
        standardTypingTest.setUserInput("Design, development and analysis of stuff");

        assertEquals("standard", standardTypingTest.getDifficulty());
        assertEquals(60, standardTypingTest.getDuration());
        assertTrue(standardTypingTest.getTestContent().length() == 1082);
        assertEquals("Design, development and analysis of stuff", standardTypingTest.getUserInput());
        assertEquals(83, standardTypingTest.getAccuracy());
        assertEquals(6, standardTypingTest.getWPM());

        hardTypingTest.setTestContent("cpsc210 syllabus");
        hardTypingTest.setUserInput("Design, development and analysis of stuff");

        assertEquals("hard", hardTypingTest.getDifficulty());
        assertEquals(60, hardTypingTest.getDuration());
        assertTrue(hardTypingTest.getTestContent().length() == 1121);
        assertEquals("Design, development and analysis of stuff", hardTypingTest.getUserInput());
        assertEquals(66, hardTypingTest.getAccuracy());
        assertEquals(6, hardTypingTest.getWPM());
    }

    @Test
    void testMultipleMethodsTogether() {
        assertEquals("standard", standardTypingTest.getDifficulty());
        assertEquals(60, standardTypingTest.getDuration());
        assertEquals("this is a test", standardTypingTest.getTestContent());
        assertEquals("this is a test", standardTypingTest.getUserInput());
        assertEquals(100, standardTypingTest.getAccuracy());
        assertEquals(4, standardTypingTest.getWPM());

        standardTypingTest.setTestContent("random words");
        standardTypingTest.setTestContent("cpsc210 syllabus");
        standardTypingTest.setUserInput("Design, development and analysis of stuff");
        standardTypingTest.setUserInput("Design, Development AND analysis! of stuff");
        standardTypingTest.setDifficulty("hard");
        standardTypingTest.setDifficulty("normal");
        standardTypingTest.setDifficulty("hard");
        standardTypingTest.setDuration(100);
        standardTypingTest.setDuration(12);
        standardTypingTest.setDuration(45);

        assertEquals("hard", standardTypingTest.getDifficulty());
        assertEquals(45, standardTypingTest.getDuration());
        assertTrue(standardTypingTest.getTestContent().length() == 1121);
        assertEquals("Design, Development AND analysis! of stuff", standardTypingTest.getUserInput());
        assertEquals(33, standardTypingTest.getAccuracy());
        assertEquals(8, standardTypingTest.getWPM());
        assertEquals(33, standardTypingTest.getAccuracy());
        assertEquals(8, standardTypingTest.getWPM());
    }
}
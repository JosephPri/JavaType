package ui.tabs;

import ui.SwingTypingTestApp;
import ui.popups.ResultsPopup;
import model.TypingTest;
import model.TypingTestHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import java.util.regex.Matcher;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

// represents home page of typing test app gui
public class HomeTab extends Tab {

    // EFFECTS: constructs a home tab for console with buttons to change test settings and start
    // MODIFIES: this
    public HomeTab(SwingTypingTestApp controller) {
       
    }

    // EFFECTS: creates difficulty button options
    // MODIFIES: this
    private void placeDifficultyButtons() {

    }

    // EFFECTS: creates testContentType button options
    // MODIFIES: this
    private void placeContentTypeButtons() {
        
    }

    // EFFECTS: creates duration button options
    // MODIFIES: this
    private void placeDurationButtons() {
        
    }

    // EFFECTS: starts the test, updating the reference text box and allowing user input
    // MODIFIES: this
    private void placeStartTestButton() {
        
    }

    // EFFECTS: creates countdown on screen
    // MODIFIES: this
    private void placeCountdown() {

    }

    // EFFECTS: creates button to load typing test history
    // MODIFIES: this
    private void placeLoadButton() {
        
    }

    // EFFECTS: creates save button to save typing test history
    // MODIFIES: this
    private void placeSaveButton() {
        
    }

    // EFFECTS: creates reference text box
    // MODIFIES: this
    private void placeRefTextBox() {

    }

    // EFFECTS: creates user input text box
    // MODIFIES: this
    private void placeUserTextBox() {
        
    }

    // EFFECTS: runs the clock and end test commands
    // MODIFIES: this
    private void runTest(int seconds, JLabel label, TypingTest test) {
        
    }
}
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
    private static final String JSON_STORE = "./data/typingTestHistory.json";   // default file storage location
    private JsonWriter jsonWriter;                                              // object to write json files
    private JsonReader jsonReader;                                              // object to read json files
    private JRadioButton standardOption;                                        // value of standard toggle
    private JRadioButton hardOption;                                            // value of hard toggle

    private JRadioButton randomWordsOption;                                     // value of random words toggle
    private JRadioButton syllabusOption;                                        // value of syllabus toggle
    private JRadioButton customContentOption;                                   // value of custom content toggle
    private JTextField customContentText;                                       // value of custom content field

    private JRadioButton thirtySecondsOption;                                   // value of 30 seconds toggle
    private JRadioButton sixtySecondsOption;                                    // value of 60 seconds toggle
    private JRadioButton customDurationOption;                                  // value of custom seconds toggle
    private JTextField customDurationText;                                      // value of custom seconds field

    private JTextArea refArea;                                                  // reference test content text area
    private JTextArea inputArea;                                                // input test text area

    private JLabel countdown;                                                   // current amount of time left

    JButton startButton;                                                        // start button object

    TypingTestHistory history;                                                  // copy of UI's typing test history

    // EFFECTS: constructs a home tab for console with buttons to change test settings and start
    // MODIFIES: this
    public HomeTab(SwingTypingTestApp controller) {
        super(controller);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        history = getController().getHistory();
        placeContentTypeButtons();
        placeDifficultyButtons();
        placeDurationButtons();
        placeStartTestButton();
        placeLoadButton();
        placeSaveButton();
        placeCountdown();
        placeRefTextBox();
        placeUserTextBox();
    }

    // EFFECTS: creates difficulty button options
    // MODIFIES: this
    private void placeDifficultyButtons() {
        standardOption = new JRadioButton("Standard", true);
        hardOption = new JRadioButton("Hard");

        ButtonGroup difficultyOptions = new ButtonGroup();
        difficultyOptions.add(standardOption);
        difficultyOptions.add(hardOption);

        JPanel difficultyPanel = new JPanel(new GridLayout(1, 2));
        difficultyPanel.setBorder(BorderFactory.createTitledBorder("Select Difficulty"));
        difficultyPanel.add(standardOption);
        difficultyPanel.add(hardOption);
        
        this.add(difficultyPanel);
    }

    // EFFECTS: creates testContentType button options
    // MODIFIES: this
    private void placeContentTypeButtons() {
        randomWordsOption = new JRadioButton("Random Words", true);
        syllabusOption = new JRadioButton("CPSC210 Syllabus");
        customContentOption = new JRadioButton("Custom:");

        ButtonGroup contentOptions = new ButtonGroup();
        contentOptions.add(randomWordsOption);
        contentOptions.add(syllabusOption);
        contentOptions.add(customContentOption);

        customContentText = new JTextField();
        customContentText.setEnabled(false);

        customContentOption.addActionListener(e -> customContentText.setEnabled(true));
        randomWordsOption.addActionListener(e -> customContentText.setEnabled(false));
        syllabusOption.addActionListener(e -> customContentText.setEnabled(false));

        JPanel contentTypePanel = new JPanel(new GridLayout(1, 1));
        contentTypePanel.setBorder(BorderFactory.createTitledBorder("Select Content Type"));

        contentTypePanel.add(randomWordsOption);
        contentTypePanel.add(syllabusOption);
        contentTypePanel.add(customContentOption);
        contentTypePanel.add(customContentText);

        this.add(contentTypePanel);
    }

    // EFFECTS: creates duration button options
    // MODIFIES: this
    private void placeDurationButtons() {
        thirtySecondsOption = new JRadioButton("30", true);
        sixtySecondsOption = new JRadioButton("60");
        customDurationOption = new JRadioButton("Custom:");

        ButtonGroup contentOptions = new ButtonGroup();
        contentOptions.add(thirtySecondsOption);
        contentOptions.add(sixtySecondsOption);
        contentOptions.add(customDurationOption);
        
        customDurationOption.addActionListener(e -> customDurationText.setEnabled(true));
        thirtySecondsOption.addActionListener(e -> customDurationText.setEnabled(false));
        sixtySecondsOption.addActionListener(e -> customDurationText.setEnabled(false));
        durationIntsOnly();

        JPanel durationPanel = new JPanel(new GridLayout(1, 1));
        durationPanel.setBorder(BorderFactory.createTitledBorder("Select Duration (seconds)"));

        durationPanel.add(thirtySecondsOption);
        durationPanel.add(sixtySecondsOption);
        durationPanel.add(customDurationOption);
        durationPanel.add(customDurationText);

        this.add(durationPanel);
    }

    // EFFECTS: ensures text field only accepts digits
    private void durationIntsOnly() {
        customDurationText = new JTextField();
        customDurationText.setEnabled(false);
        ((AbstractDocument) customDurationText.getDocument()).setDocumentFilter(new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d*");

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });
    }

    // EFFECTS: starts the test, updating the reference text box and allowing user input
    // MODIFIES: this
    private void placeStartTestButton() {
        JPanel startBlock = new JPanel();
        startButton = new JButton("Start Test");
        startBlock.add(formatButtonRow(startButton));
    
        startButton.addActionListener(e -> {
            if (e.getActionCommand().equals("Start Test")) {
                String difficulty = hardOption.isSelected() ? "hard" : "standard";
                int duration = sixtySecondsOption.isSelected() ? 60 
                            : customDurationOption.isSelected() ? Integer.parseInt(customDurationText.getText()) 
                            : 30;
                String content = syllabusOption.isSelected() ? "cpsc210 syllabus" 
                            : customContentOption.isSelected() ? customContentText.getText() 
                            : "random words";
                setupTest(new TypingTest(difficulty, duration, content, null));
            }
        });

        this.add(startBlock);
    }

    // EFFECTS: helper function setup test environment
    // MODIFIES: this
    private void setupTest(TypingTest test) {
        refArea.setText(test.getTestContent());
        inputArea.setEnabled(true);
        inputArea.setText("");
        startButton.setEnabled(false);
        runTest(test.getDuration(), countdown, test);
    }

    // EFFECTS: creates countdown on screen
    // MODIFIES: this
    private void placeCountdown() {
        countdown = new JLabel("Time Remaining: 0", JLabel.RIGHT);
        this.add(countdown);
        ImageIcon difficultyIcon = new ImageIcon("./data/clocktiny.gif");
        JLabel imageLabel = new JLabel(difficultyIcon);
        this.add(imageLabel);
    }

    // EFFECTS: creates button to load typing test history
    // MODIFIES: this
    private void placeLoadButton() {
        JButton loadButton = new JButton("Load");

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getController().setHistory(jsonReader.read());
                    System.out.println("Loaded typing test history from " + JSON_STORE);
                } catch (IOException e1) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
            }
        });
        add(loadButton);
        setVisible(true);
    }

    // EFFECTS: creates save button to save typing test history
    // MODIFIES: this
    private void placeSaveButton() {
        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(history);
                    jsonWriter.close();
                    System.out.println("Saved typing test history to " + JSON_STORE);
                } catch (FileNotFoundException e2) {
                    System.out.println("Unable to write to file: " + JSON_STORE);
                }
            }
        });

        add(saveButton);
        setVisible(true);
    }

    // EFFECTS: creates reference text box
    // MODIFIES: this
    private void placeRefTextBox() {
        refArea = new JTextArea(12, 50);
        refArea.setEnabled(false);
        refArea.setLineWrap(true);
        refArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(refArea);

        this.add(scrollPane);
    }

    // EFFECTS: creates user input text box
    // MODIFIES: this
    private void placeUserTextBox() {
        inputArea = new JTextArea(12, 50);
        inputArea.setEnabled(false);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(inputArea);

        this.add(scrollPane);
    }

    // EFFECTS: runs the clock and end test commands
    // MODIFIES: this
    private void runTest(int seconds, JLabel label, TypingTest test) {
        label.setText("Time Remaining: " + seconds);
        final Timer t = new Timer(1000, new ActionListener() {
            private long time = seconds * 1000;

            public void actionPerformed(ActionEvent e) {
                if (time > 0) {
                    time -= 1000;
                    label.setText("Time Remaining: " + Long.toString(time / 1000));
                } else {
                    ((Timer) e.getSource()).stop();
                    inputArea.setEnabled(false);
                    startButton.setEnabled(true);
                    test.setUserInput(inputArea.getText());
                    getController().getHistory().addTest(test);
                    new ResultsPopup(test);
                }
            }
        });
        t.start();
    }
}
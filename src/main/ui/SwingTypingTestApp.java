package ui;

import model.TypingTest;
import model.TypingTestHistory;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.HomeTab;
import ui.tabs.HistoryTab;

import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Swing based typing test application
public class SwingTypingTestApp extends JFrame {
    private static final String JSON_STORE = "./data/typingTestHistory.json"; // default file storage location
    private TypingTestHistory history; // list of previous typing tests
    private Scanner input; // general input given by user
    private JsonWriter jsonWriter; // object to write json files
    private JsonReader jsonReader; // object to read json files

    public static final int HOME_TAB_INDEX = 0; // index for home tab
    public static final int HISTORY_TAB_INDEX = 1; // index for history tab
    public static final int WIDTH = 690; // width of GUI window
    public static final int HEIGHT = 650; // height of GUI window
    private JTabbedPane topbar; // tab selector

    // EFFECTS: runs the typing test application GUI
    public SwingTypingTestApp() throws FileNotFoundException {
        super("Typing Test App");
        setLocationRelativeTo(null);
        init();
        swingSetup();
    }

    // MODIFIES: this
    // EFFECTS: sets up swing gui window features
    private void swingSetup() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        topbar = new JTabbedPane();
        topbar.setTabPlacement(JTabbedPane.TOP);
        loadTabs();
        add(topbar);
        topbar.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (topbar.getSelectedIndex() == HISTORY_TAB_INDEX) {
                    ((HistoryTab) topbar.getComponentAt(HISTORY_TAB_INDEX)).updateHistory();
                }
            }
        });
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes objects
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        history = new TypingTestHistory();
    }

    // MODIFIES: this
    // EFFECTS: adds home tab and history tab and report tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel historyTab = new HistoryTab(this);

        topbar.add(homeTab, HOME_TAB_INDEX);
        topbar.setTitleAt(HOME_TAB_INDEX, "Home");

        topbar.add(historyTab, HISTORY_TAB_INDEX);
        topbar.setTitleAt(HISTORY_TAB_INDEX, "History");
    }

    // EFFECTS: returns TypingTestHistory object controlled by this UI
    public TypingTestHistory getHistory() {
        return history;
    }

    // EFFECTS: sets the TypingTestHistory object controlled by this UI
    public void setHistory(TypingTestHistory history) {
        this.history = history;
    }
}

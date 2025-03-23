package ui;



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

    }

    // MODIFIES: this
    // EFFECTS: sets up swing gui window features
    private void swingSetup() {
        
    }

    // MODIFIES: this
    // EFFECTS: initializes objects
    private void init() {

    }

    // MODIFIES: this
    // EFFECTS: adds home tab and history tab and report tab to this UI
    private void loadTabs() {
        
    }

    // EFFECTS: returns TypingTestHistory object controlled by this UI
    public TypingTestHistory getHistory() {
        
    }
}

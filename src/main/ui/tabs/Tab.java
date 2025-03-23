package ui.tabs;

import ui.SwingTypingTestApp;

import javax.swing.*;
import java.awt.*;

// represents an abstract tab of the gui window
public abstract class Tab extends JPanel {

    private final SwingTypingTestApp controller;

    //REQUIRES: SwingTypingTestApp controller that holds this tab
    public Tab(SwingTypingTestApp controller) {
        
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        
    }

    //EFFECTS: returns the SwingTypingTestApp controller for this tab
    public SwingTypingTestApp getController() {

    }

}

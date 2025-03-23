package ui.tabs;

import ui.SwingTypingTestApp;

import javax.swing.*;
import java.awt.*;

// represents an abstract tab of the gui window
public abstract class Tab extends JPanel {

    private final SwingTypingTestApp controller;

    //REQUIRES: SwingTypingTestApp controller that holds this tab
    public Tab(SwingTypingTestApp controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the SwingTypingTestApp controller for this tab
    public SwingTypingTestApp getController() {
        return controller;
    }

}

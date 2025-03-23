package ui.popups;

import javax.swing.*;

// represents an abstract popup window
public abstract class Popup extends JDialog {
    
    //constructs popup window with no features
    public Popup(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setLocationRelativeTo(null);
        setModal(true);
    }
}
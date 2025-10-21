    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.*;


public class GUI {
    private static final int X_LOC = 100;
    private static final int Y_LOC = 100;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String NAME = "Coffee Shop";
    private JFrame frame;

    public GUI() {
        createFrame();
        displayFrame();
    }

    public void createFrame() {
        frame = new JFrame();
        frame.setTitle(NAME);
        frame.setLocation(X_LOC, Y_LOC);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    private void displayFrame() {
        frame.pack();
        frame.setVisible(true);
    }
}

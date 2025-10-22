import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | 
                     InstantiationException | IllegalAccessException e) {
                // Fallback to default look and feel
            }
            
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}

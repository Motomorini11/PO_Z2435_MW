import javax.swing.*;

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new MainMenu().setVisible(true);
        }
    });
}
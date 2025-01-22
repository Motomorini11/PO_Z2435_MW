import javax.swing.*;
import java.awt.*;

public class TutorialDialog extends JDialog {
    public TutorialDialog(JFrame parent) {
        super(parent, "Tutorial", true);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.6);
        int height = (int) (screenSize.height * 0.6);
        setSize(width, height);
        setLocationRelativeTo(parent);


        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);

        // Tutorial text
        JLabel tutorialText = new JLabel("<html><div style='text-align: center; color: white; font-size: 20px;'>"
                + "Welcome to Castle Craft!<br><br>"
                + "Here's how to play the game:<br>"
                + "1. Main goal of the game is to reach the highest turn number.<br>"
                + "2. Upgrading the main castle increases your max energy and taxes.<br> "
                + "3. Gather resources by producing them inside buildings.<br>"
                + "4. Production costs energy and uses workers, every worker can work only once per turn.<br>"
                + "5. Workers need to eat food every turn! They die if they don't (I know crazy).<br>"
                + "5. Ending the turn refreshes energy and available free workers.<br>"
                + "6. Survive enemy attacks by producing swords for defense.<br>"
                + "7. Use resources to upgrade the buildings, increasing your production.<br><br>"
                + "Good luck and have fun!"
                + "</div></html>");
        tutorialText.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(tutorialText, BorderLayout.CENTER);

        JButton closeButton = new JButton("Start Game");
        closeButton.setFont(new Font("Arial", Font.BOLD, 18));
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(closeButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel);
    }
}

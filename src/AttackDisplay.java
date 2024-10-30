import javax.swing.*;
import java.awt.*;

public class AttackDisplay extends JComponent {
    private JLabel countdownLabel;
    private JLabel powerLabel;

    public AttackDisplay(int turnsToNextAttack, int approximatePower) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        countdownLabel = new JLabel("Atak za: " + turnsToNextAttack +" tur.");
        powerLabel = new JLabel("Moc ataku: " + approximatePower);

        // Customize font, color, and alignment
        Font displayFont = new Font("Arial", Font.BOLD, 27);
        countdownLabel.setFont(displayFont);
        powerLabel.setFont(displayFont);

        countdownLabel.setForeground(Color.RED);
        powerLabel.setForeground(Color.RED);

        // Add labels to the component
        add(countdownLabel);
        add(powerLabel);
    }

    // Update method to change countdown and power display
    public void updateDisplay(int turnsToNextAttack, int approximatePower) {
        countdownLabel.setText("Atak za: " + turnsToNextAttack +" tur.");
        powerLabel.setText("Moc ataku: " + approximatePower);

    }

    // Custom background painting
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw semi-transparent background
        g2d.setColor(new Color(0, 0, 0, 128));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

        g2d.dispose();
        super.paintComponent(g); // Draw text labels
    }
}

import javax.swing.*;
import java.awt.*;

public class ResourceDisplay extends JComponent {
    private ImageIcon resourceIcon;
    private JLabel resourceLabel;
    private int maxCount;
    private int usedCount;
    private boolean showDetailedFormat; // Flag to control display format

    // Constructor with showDetailedFormat flag
    public ResourceDisplay(ImageIcon icon, int count, int width, int height, boolean showDetailedFormat) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);

        this.resourceIcon = resizeIcon(icon, width, height);
        JLabel iconLabel = new JLabel(resourceIcon);

        this.showDetailedFormat = showDetailedFormat; // Set the format flag
        this.maxCount = 0;
        this.usedCount = 0;

        // Initialize label based on format
        resourceLabel = new JLabel(formatDisplayText(count));
        resourceLabel.setFont(new Font("Arial", Font.BOLD, 24));
        resourceLabel.setForeground(Color.WHITE);

        add(iconLabel);
        add(Box.createRigidArea(new Dimension(0, 0))); // Space between icon and count text
        add(resourceLabel);
    }

    public void updateCount(int newCount) {
        resourceLabel.setText(String.valueOf(newCount));
    }

    // Method to update the count, maxCount, and usedCount displayed
    public void updateCounts(int count, int maxCount, int usedCount) {
        this.maxCount = maxCount;
        this.usedCount = usedCount;
        resourceLabel.setText(formatDisplayText(count));
    }

    // Helper method to format the display text based on flag
    private String formatDisplayText(int count) {
        if (showDetailedFormat) {
            return count + " / " + maxCount + " (" + usedCount + ")";
        } else {
            return String.valueOf(count);
        }
    }

    // Method to resize the icon
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 0, 0, 128)); // Semi-transparent background
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2d.dispose();
        super.paintComponent(g);
    }
}

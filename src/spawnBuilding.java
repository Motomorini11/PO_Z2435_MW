import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class spawnBuilding extends JPanel {

    public spawnBuilding(int x, int y, int width, int height, int r, int g, int b, String text) {
        setLayout(new BorderLayout());
        setBackground(new Color(r, g, b));
        setBounds(x, y, width, height);

        // Add text label to the rectangle
        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setForeground(Color.WHITE); // Set text color
        textLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(textLabel, BorderLayout.CENTER);

        // Add a mouse listener to handle clicks on the rectangle
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onBuildingClick();
            }
        });
    }


    private void onBuildingClick() {
        System.out.println("Rectangle clicked!"); // Placeholder for future action
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;


public class RectangleSpawner {
    private Game game;

    private static Map<Building, String> buildingNames = new HashMap<>();

    /**
     * Spawns a customizable rectangle with specified parameters.
     *
     * @param parentPanel The JPanel to which the rectangle should be added.
     * @param x           X-coordinate for the rectangle.
     * @param y           Y-coordinate for the rectangle.
     * @param width       Width of the rectangle.
     * @param height      Height of the rectangle.
     * @param red          Red component of the rectangle color.
     * @param green         Green component of the rectangle color.
     * @param blue           Blue component of the rectangle color.
     * @param alpha       Transparency level (0 to 255).
     * @param name        Text to display inside the rectangle.
     * @param fontName    Font name for the text.
     * @param fontSize    Font size for the text.
     * @param building    Type of the building
     * @param infoPanel   Type of info displayed
     */

    public static void spawnRectangle(JPanel parentPanel, int x, int y, int width, int height, int red, int green, int blue, int alpha,
                                      String name, String fontName, int fontSize, Building building, InfoPanel infoPanel) {

        buildingNames.put(building, name);


        JPanel rectanglePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                Color backgroundColor = new Color(red, green, blue, alpha);
                g2d.setColor(backgroundColor);
                g2d.fillRect(0, 0, width, height);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(0, 0, width - 1, height - 1);

                if (name != null && !name.isEmpty()) {
                    g2d.setFont(new Font(fontName, Font.PLAIN, fontSize));
                    FontMetrics metrics = g2d.getFontMetrics();
                    int textX = (width - metrics.stringWidth(name)) / 2;
                    int textY = (height - metrics.getHeight()) / 2 + metrics.getAscent();
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(name, textX, textY);
                }
            }
        };

        rectanglePanel.setBounds(x, y, width, height);
        rectanglePanel.setOpaque(false);

        // Add click listener to show the info panel for this building
        rectanglePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                infoPanel.show(building);
            }
        });

        parentPanel.add(rectanglePanel);
        parentPanel.repaint();
    }

    /**
     * Retrieves the name associated with a building.
     *
     * @param building The building to look up.
     * @return The name of the building.
     */
    public static String getBuildingName(Building building) {
        return buildingNames.getOrDefault(building, "Unknown Building");
    }
}


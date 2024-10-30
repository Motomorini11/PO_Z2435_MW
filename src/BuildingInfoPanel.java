import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuildingInfoPanel extends JPanel {
    private JLabel buildingNameLabel;
    private JLabel buildingLevelLabel;
    private JLabel energyCostLabel;
    private JLabel workerCostLabel;
    private JLabel yieldLabel;
    private RectangleButton startProductionButton;
    private RectangleButton upgradeButton;

    private String buildingName;
    private int buildingLevel;
    private int energyCost;
    private int workerCost;
    private int yield;
    private Game game;

    public BuildingInfoPanel(String buildingName, int buildingLevel, int energyCost, int workerCost, int yield, Game game) {
        this.buildingName = buildingName;
        this.buildingLevel = buildingLevel;
        this.energyCost = energyCost;
        this.workerCost = workerCost;
        this.yield = yield;
        this.game = game;

        setLayout(null);
        setBounds(100, 600, 300, 400); // Fixed position for the panel
        setBackground(new Color(50, 50, 50, 200));

        // Setup labels for building info
        buildingNameLabel = createLabel(buildingName, 20, 20, 250, 30);
        buildingLevelLabel = createLabel("Level: " + buildingLevel, 220, 20, 70, 30);
        energyCostLabel = createLabel("Energy Cost: " + energyCost, 20, 80, 250, 30);
        workerCostLabel = createLabel("Worker Cost: " + workerCost, 20, 120, 250, 30);
        yieldLabel = createLabel("Yield: " + yield, 20, 160, 250, 30);

        // Create start production button
        startProductionButton = new RectangleButton("Start Production", 20, 220, 250, 50);
        startProductionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startProduction();
            }
        });

        // Create upgrade button
        upgradeButton = new RectangleButton("Upgrade Building", 20, 290, 250, 50);
        upgradeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                upgradeBuilding(); // Placeholder for future implementation
            }
        });

        // Add components to panel
        add(buildingNameLabel);
        add(buildingLevelLabel);
        add(energyCostLabel);
        add(workerCostLabel);
        add(yieldLabel);
        add(startProductionButton);
        add(upgradeButton);
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void startProduction() {
        if (game.deductEnergy(energyCost) && game.deductWorkers(workerCost)) {
            System.out.println("Production started for " + buildingName);
            // Logic to mark production as active in the game
        } else {
            JOptionPane.showMessageDialog(this, "Not enough resources to start production.");
        }
    }

    private void upgradeBuilding() {
        System.out.println("Upgrade building logic placeholder");
    }
}

class RectangleButton extends JPanel {
    public RectangleButton(String text, int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setBackground(new Color(100, 100, 100));
        setLayout(new BorderLayout());

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        add(label, BorderLayout.CENTER);

        // Add border to simulate button appearance
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }
}

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class InfoPanel extends JPanel {
    private Building currentBuilding;
    private ResourceDisplay goldupgrade;
    private ResourceDisplay woodupgrade;
    private ResourceDisplay stoneupgrade;

    public InfoPanel() {
        setLayout(null);
        setBorder(new LineBorder(Color.BLACK, 2));
        setOpaque(false);
        setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the background color to light brown (RGB: 210, 180, 140)
        g.setColor(new Color(161, 102, 47));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void show(Building building) {
        if (isVisible() && currentBuilding == building) {
            return;
        }
        currentBuilding = building;
        removeAll(); // Clear previous components

        int yPosition = 0; // Starting Y position for label placement

        // Title label
        JLabel titleLabel = new JLabel(RectangleSpawner.getBuildingName(currentBuilding));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(10, yPosition, 280, 30);
        add(titleLabel);

        JLabel levellabel = new JLabel("Lvl. " + currentBuilding.getLevel());
        levellabel.setForeground(Color.WHITE);
        levellabel.setFont(new Font("Arial", Font.BOLD, 18));
        levellabel.setBounds(100, yPosition, 280, 30);
        add(levellabel);

        JPanel titleBelt = new JPanel();
        titleBelt.setBounds(0, yPosition, getWidth(), 40);
        titleBelt.setBackground(new Color(0, 0, 0, 180));
        titleBelt.setLayout(null);
        add(titleBelt);
        yPosition += 40;

        Font infoFont = new Font("Arial", Font.PLAIN, 14);

        // Display building information with custom positioning
        JLabel energyLabel = new JLabel("Energy Cost: " + building.getEnergyCost());
        energyLabel.setForeground(Color.WHITE);
        energyLabel.setFont(infoFont);
        energyLabel.setBounds(10, yPosition, 200, 25);
        add(energyLabel);
        yPosition += 40;

        JLabel workersLabel = new JLabel("Workers Required: " + building.getWorkersRequired());
        workersLabel.setForeground(Color.WHITE);
        workersLabel.setFont(infoFont);
        workersLabel.setBounds(10, yPosition, 200, 25);
        add(workersLabel);
        yPosition += 40;

        JLabel productionLabel = new JLabel("Production Output: " + building.getProductionOutput());
        productionLabel.setForeground(Color.WHITE);
        productionLabel.setFont(infoFont);
        productionLabel.setBounds(10, yPosition, 200, 25);
        add(productionLabel);
        yPosition += 40;

        JLabel timeLabel = new JLabel("Production Time: " + building.getProductionTime() + " turns");
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(infoFont);
        timeLabel.setBounds(10, yPosition, 200, 25);
        add(timeLabel);
        yPosition += 50;

        // Start Production button
        JButton startButton = new JButton("Produce");
        startButton.setBounds(10, yPosition, 150, 30);
        startButton.setBackground(new Color(169, 169, 169));
        startButton.setForeground(Color.BLACK);
        startButton.setBorder(new LineBorder(Color.BLACK, 1));
        startButton.setFont(new Font("Arial", Font.PLAIN, 12));
        add(startButton);
        yPosition += 50;

        // Upgrade button
        JButton upgradeButton = new JButton("Upgrade");
        upgradeButton.setBounds(10, yPosition, 150, 30);
        upgradeButton.setBackground(new Color(255, 223, 0));
        upgradeButton.setForeground(Color.BLACK);
        upgradeButton.setBorder(new LineBorder(Color.BLACK, 1));
        upgradeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        add(upgradeButton);
        yPosition += 40;


        if (building.getGoldUpgradeCost() > 0) {
            goldupgrade = new ResourceDisplay(new ImageIcon("images/gold.png"),building.getGoldUpgradeCost(),40,40,false);
            goldupgrade.setBounds(10, yPosition, 75, 50);
            add(goldupgrade);
        }

        if (building.getWoodUpgradeCost() > 0) {
            woodupgrade = new ResourceDisplay(new ImageIcon("images/wood.png"),building.getWoodUpgradeCost(),40,40,false);
            woodupgrade.setBounds(100, yPosition, 75, 50);
            add(woodupgrade);
        }

        if (building.getStoneUpgradeCost() > 0) {
            stoneupgrade = new ResourceDisplay(new ImageIcon("images/stone.png"),building.getStoneUpgradeCost(),40,40,false);
            stoneupgrade.setBounds(200, yPosition, 75, 50);
            add(stoneupgrade);
        }



        setVisible(true);
        revalidate();
        repaint();
    }
}


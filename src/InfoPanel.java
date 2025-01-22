import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class InfoPanel extends JPanel {
    private Building currentBuilding;
    private ResourceDisplay goldupgrade;
    private ResourceDisplay woodupgrade;
    private ResourceDisplay stoneupgrade;
    private Game game;

    public InfoPanel(Game game) {
        this.game = game;
        setLayout(null);
        setBorder(new LineBorder(Color.BLACK, 2));
        setOpaque(false);
        setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(161, 102, 47));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void show(Building building) {
        if (isVisible() && currentBuilding == building) {
            removeAll();
            return;
        }

        currentBuilding = building;
        setVisible(false);
        removeAll();

        int yPosition = 0;

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

        if (building.getEnergyCost() > 0) {
            JLabel energyLabel = new JLabel("Energy Cost: " + building.getEnergyCost());
            energyLabel.setForeground(Color.WHITE);
            energyLabel.setFont(infoFont);
            energyLabel.setBounds(10, yPosition, 200, 25);
            add(energyLabel);
            yPosition += 40;
        }

        if (building.getWorkersRequired() > 0) {
            JLabel workersLabel = new JLabel("Workers Required: " + building.getWorkersRequired());
            workersLabel.setForeground(Color.WHITE);
            workersLabel.setFont(infoFont);
            workersLabel.setBounds(10, yPosition, 200, 25);
            add(workersLabel);
            yPosition += 40;
        }


        if (building.getProductionOutput() > 0) {
            JLabel productionLabel = new JLabel("Production Output: " + building.getProductionOutput());
            productionLabel.setForeground(Color.WHITE);
            productionLabel.setFont(infoFont);
            productionLabel.setBounds(10, yPosition, 200, 25);
            add(productionLabel);
            yPosition += 40;

        //production button
        if (building.getEnergyCost()>0) {
            JButton startButton = new JButton("Produce");
            startButton.setBounds(10, yPosition, 150, 30);
            startButton.setBackground(new Color(169, 169, 169));
            startButton.setForeground(Color.BLACK);
            startButton.setBorder(new LineBorder(Color.BLACK, 1));
            startButton.setFont(new Font("Arial", Font.PLAIN, 12));
            startButton.addActionListener(e -> {
                if (building.getEnergyCost() <= game.getEnergy() && building.getWorkersRequired() <= game.getWorkers()) {
                    String buildingName = RectangleSpawner.getBuildingName(building);

                    switch (buildingName) {
                        case "Farm":
                            game.deductEnergy(building.getEnergyCost());
                            game.deductWorkers(building.getWorkersRequired());
                            game.addFood(building.getProductionOutput());
                            break;
                        case "Sawmill":
                            game.deductEnergy(building.getEnergyCost());
                            game.deductWorkers(building.getWorkersRequired());
                            game.addwood(building.getProductionOutput());
                            break;
                        case "Quarry":
                            game.deductEnergy(building.getEnergyCost());
                            game.deductWorkers(building.getWorkersRequired());
                            game.addstone(building.getProductionOutput());
                            break;
                        case "House":
                            game.deductEnergy(building.getEnergyCost());
                            game.deductWorkers(building.getWorkersRequired());
                            game.addWorkers(building.getProductionOutput());
                            break;
                        case "Armory":
                            game.deductEnergy(building.getEnergyCost());
                            game.deductWorkers(building.getWorkersRequired());
                            game.addsword(building.getProductionOutput());
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "This building cannot produce resources!");
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough resources to start production!");
                }
            });
            add(startButton);
            yPosition += 50;
        }
        }

        // Upgrade button
        JButton upgradeButton = new JButton("Upgrade");
        upgradeButton.setBounds(10, yPosition, 150, 30);
        upgradeButton.setBackground(new Color(255, 223, 0));
        upgradeButton.setForeground(Color.BLACK);
        upgradeButton.setBorder(new LineBorder(Color.BLACK, 1));
        upgradeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        upgradeButton.addActionListener(e -> {
            if (building.getGoldUpgradeCost() <= game.getGold() &&
                    building.getWoodUpgradeCost() <= game.getWood() &&
                    building.getStoneUpgradeCost() <= game.getStone()) {


                game.deductGold(building.getGoldUpgradeCost());
                game.deductWood(building.getWoodUpgradeCost());
                game.deductStone(building.getStoneUpgradeCost());

                String buildingName = RectangleSpawner.getBuildingName(building);


                switch (buildingName) {
                    case "Farm": // Farm produces food
                        building.setProductionOutput(building.getProductionOutput() + 10);
                        building.setGoldUpgradeCost(building.getGoldUpgradeCost() + 2);
                        building.setWoodUpgradeCost(building.getWoodUpgradeCost() + 5);
                        building.setStoneUpgradeCost(building.getStoneUpgradeCost() + 5);
                        break;

                    case "Sawmill": // Sawmill produces wood
                        building.setProductionOutput(building.getProductionOutput() + 5);
                        building.setGoldUpgradeCost(building.getGoldUpgradeCost() + 1);
                        building.setWoodUpgradeCost(building.getWoodUpgradeCost() + 10);
                        building.setStoneUpgradeCost(building.getStoneUpgradeCost() + 5);
                        break;

                    case "Quarry": // Quarry produces stone
                        building.setProductionOutput(building.getProductionOutput() + 5);
                        building.setGoldUpgradeCost(building.getGoldUpgradeCost() + 1);
                        building.setWoodUpgradeCost(building.getWoodUpgradeCost() + 5);
                        building.setStoneUpgradeCost(building.getStoneUpgradeCost() + 10);
                        break;

                    case "House": // House adds workers
                        building.setProductionOutput(building.getProductionOutput() + 1);
                        building.setGoldUpgradeCost(building.getGoldUpgradeCost() + 10);
                        building.setWoodUpgradeCost(building.getWoodUpgradeCost() + 10);
                        building.setStoneUpgradeCost(building.getStoneUpgradeCost() + 10);
                        break;

                    case "Armory": // Armory produces swords
                        building.setProductionOutput(building.getProductionOutput() + 3);
                        building.setGoldUpgradeCost(building.getGoldUpgradeCost() + 10);
                        building.setWoodUpgradeCost(building.getWoodUpgradeCost() + 15);
                        building.setStoneUpgradeCost(building.getStoneUpgradeCost() + 15);
                        break;
                    case "Castle":
                        game.addEnergy(building.getProductionOutput());
                        game.addTax(1);
                        building.setProductionOutput(building.getProductionOutput() + 2);
                        building.setGoldUpgradeCost(building.getGoldUpgradeCost() + 10);
                        building.setWoodUpgradeCost(building.getWoodUpgradeCost() + 10);
                        building.setStoneUpgradeCost(building.getStoneUpgradeCost() + 10);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "This building cannot be upgraded!");
                        return;
                }

                building.setLevel(building.getLevel() + 1);

            } else {
                JOptionPane.showMessageDialog(null, "Not enough resources to upgrade!");
            }
        });
        add(upgradeButton);
        yPosition += 40;

        if (building.getGoldUpgradeCost() > 0) {
            goldupgrade = new ResourceDisplay(new ImageIcon("images/gold.png"), building.getGoldUpgradeCost(), 40, 40, false);
            goldupgrade.setBounds(10, yPosition, 75, 50);
            add(goldupgrade);
        }

        if (building.getWoodUpgradeCost() > 0) {
            woodupgrade = new ResourceDisplay(new ImageIcon("images/wood.png"), building.getWoodUpgradeCost(), 40, 40, false);
            woodupgrade.setBounds(100, yPosition, 75, 50);
            add(woodupgrade);
        }

        if (building.getStoneUpgradeCost() > 0) {
            stoneupgrade = new ResourceDisplay(new ImageIcon("images/stone.png"), building.getStoneUpgradeCost(), 40, 40, false);
            stoneupgrade.setBounds(200, yPosition, 75, 50);
            add(stoneupgrade);
        }

        revalidate();
        repaint();
        setVisible(true);
    }
}

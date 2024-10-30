import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends JFrame {
    private Game game;
    private JPanel mainPanel;
    private ResourceDisplay energyDisplay;
    private ResourceDisplay woodDisplay;
    private ResourceDisplay stoneDisplay;
    private ResourceDisplay foodDisplay;
    private ResourceDisplay goldDisplay;
    private ResourceDisplay workersDisplay;
    private ResourceDisplay swordDisplay;
    private ResourceDisplay toolDisplay;
    private ResourceDisplay ironDisplay;
    private JLabel turnCounterLabel;
    private int nextAttackTurn;
    private int attackPower;
    private AttackDisplay attackDisplay;
    private BuildingInfoPanel buildingInfoPanel;

    public GameWindow() {
        setTitle("Castle Craft - Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setSize(1920, 1080);

        game = new Game();

        // Main panel setup
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);

        energyDisplay = new ResourceDisplay(new ImageIcon("images/energy.png"), game.getEnergy(), 75, 75,false);
        energyDisplay.setBounds(1545, 70, 150, 75);
        mainPanel.add(energyDisplay);

        woodDisplay = new ResourceDisplay(new ImageIcon("images/wood.png"), game.getWood(), 75, 75,false);
        woodDisplay.setBounds(1545, 500, 150, 75);
        mainPanel.add(woodDisplay);

        stoneDisplay = new ResourceDisplay(new ImageIcon("images/stone.png"), game.getStone(), 75, 75,false);
        stoneDisplay.setBounds(1715, 500, 150, 75);
        mainPanel.add(stoneDisplay);

        foodDisplay = new ResourceDisplay(new ImageIcon("images/food.png"), game.getFood(), 75, 75,false);
        foodDisplay.setBounds(1715, 290, 150, 75);
        mainPanel.add(foodDisplay);

        goldDisplay = new ResourceDisplay(new ImageIcon("images/gold.png"), game.getGold(), 75, 75,false);
        goldDisplay.setBounds(1545, 290, 150, 75);
        mainPanel.add(goldDisplay);

        workersDisplay = new ResourceDisplay(new ImageIcon("images/man.png"), game.getWorkers(), 75, 75,true);
        workersDisplay.updateCounts(game.getWorkers(), game.getMaxWorkers(),game.getFreeWorkers());
        workersDisplay.setBounds(1715, 70, 160, 75);
        mainPanel.add(workersDisplay);

        toolDisplay = new ResourceDisplay(new ImageIcon("images/tool.png"), game.getTool(), 75, 75,false);
        toolDisplay.setBounds(1545, 710, 150, 75);
        mainPanel.add(toolDisplay);

        ironDisplay = new ResourceDisplay(new ImageIcon("images/iron.png"), game.getIron(), 75, 75,false);
        ironDisplay.setBounds(1545, 920, 150, 75);
        mainPanel.add(ironDisplay);

        swordDisplay = new ResourceDisplay(new ImageIcon("images/sword.png"), game.getSword(), 75, 75,false);
        swordDisplay.setBounds(1715, 710, 150, 75);
        mainPanel.add(swordDisplay);

        setupTurnCounter(mainPanel, 80, 50, 60, "Arial");

        nextAttackTurn = calculateNextAttackTurn(game.getTurn());
        attackPower = calculateAttackPower(game.getTurn());

        // Create and add the AttackDisplay to show attack countdown and power
        attackDisplay = new AttackDisplay(nextAttackTurn - game.getTurn(), attackPower);
        attackDisplay.setBounds(75, 290, 280, 80);
        mainPanel.add(attackDisplay);

        spawnBuilding(1000, 700, 200, 100, 0, 200, 0, "Tartak", 1, 5, 2, 10);


        // Return button setup
        JButton returnButton = new JButton("Powrót");
        returnButton.setFont(new Font("Arial", Font.BOLD, 18));
        returnButton.setBounds(40, 1010, 120, 40);
        returnButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, "Wyjść?", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                game.storeHighestTurn();
                dispose();
                new MainMenu().setVisible(true);
            }
        });
        mainPanel.add(returnButton);

        // End Turn button setup
        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.setFont(new Font("Arial", Font.BOLD, 25));
        endTurnButton.setBounds(1715, 920, 150, 75);
        endTurnButton.addActionListener(e -> {
            game.nextTurn();
            checkForAttack();
            updateAllResources();
            updateTurnCounter();
            workersDisplay.updateCounts(game.getWorkers(), game.getMaxWorkers(),game.getFreeWorkers());
            if (game.checkForGameOver()) {
                JOptionPane.showMessageDialog(this, "Game Over! You survived " + game.getTurn() + " turns.");
                dispose();
                new MainMenu().setVisible(true);
            }
        });
        mainPanel.add(endTurnButton);

        // Playable area setup with background image
        JLabel playableArea = new JLabel();
        try {
            Image backgroundImage = ImageIO.read(new File("images/BackG.png"));
            playableArea.setIcon(new ImageIcon(backgroundImage.getScaledInstance(1920, 1080, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        playableArea.setBounds(0, 0, 1920, 1080);
        mainPanel.add(playableArea);

        add(mainPanel);
        setVisible(true);
    }

    public void updateAllResources() {
        energyDisplay.updateCount(game.getEnergy());
        woodDisplay.updateCount(game.getWood());
        stoneDisplay.updateCount(game.getStone());
        foodDisplay.updateCount(game.getFood());
        goldDisplay.updateCount(game.getGold());
        workersDisplay.updateCount(game.getWorkers());
        toolDisplay.updateCount(game.getTool());
        ironDisplay.updateCount(game.getIron());
        swordDisplay.updateCount(game.getSword());

    }

    private void setupTurnCounter(JPanel mainPanel, int x, int y, int fontSize, String fontName) {
        turnCounterLabel = new JLabel("Tura: " + game.getTurn());
        turnCounterLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
        turnCounterLabel.setForeground(Color.WHITE);
        turnCounterLabel.setBounds(x, y, 250, 100);

        // Create a semi-transparent background color
        Color semiTransparentBackground = new Color(0, 0, 0, 128);

        turnCounterLabel.setOpaque(true);
        turnCounterLabel.setBackground(semiTransparentBackground);
        mainPanel.add(turnCounterLabel);
    }

    private void checkForAttack() {
        int currentTurn = game.getTurn();

        if (currentTurn >= nextAttackTurn) {
            // Check if player has enough weapons to survive the attack
            if (game.getSword() >= attackPower) {
                game.useSword(attackPower);
                nextAttackTurn = calculateNextAttackTurn(currentTurn); // Schedule next attack
                attackPower = calculateAttackPower(currentTurn); // Increase attack power

                // Update the AttackDisplay with the new countdown and power
                attackDisplay.updateDisplay(nextAttackTurn - currentTurn, attackPower);
            } else {
                // Game over if weapons are insufficient
                JOptionPane.showMessageDialog(this, "Game Over! Nie udało ci się obronić zamku.");
                dispose();
                new MainMenu().setVisible(true);
            }
        } else {
            // Update countdown if no attack this turn
            attackDisplay.updateDisplay(nextAttackTurn - currentTurn, attackPower);

        }

    }

    // Function to calculate the turn number for the next attack
    private int calculateNextAttackTurn(int currentTurn) {
        return currentTurn + (int) (10 + Math.log(currentTurn +1) * 10 - currentTurn); // Adjust frequency as needed
    }

    // Function to calculate attack power based on current turn
    private int calculateAttackPower(int currentTurn) {
        return (int) (20 + currentTurn * 1.5); // Adjust power growth rate as needed
    }
    private void updateTurnCounter() {
        turnCounterLabel.setText("Tura: " + game.getTurn());
        repaint();
    }

    public void spawnBuilding(int x, int y, int width, int height, int r, int g, int b, String buildingName, int buildingLevel, int energyCost, int workerCost, int yield) {
        spawnBuilding building = new spawnBuilding(x, y, width, height, r, g, b, buildingName);
        building.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showBuildingInfo(buildingName, buildingLevel, energyCost, workerCost, yield);
            }
        });
        mainPanel.add(building);
        repaint();
    }

    private void showBuildingInfo(String buildingName, int buildingLevel, int energyCost, int workerCost, int yield) {
        if (buildingInfoPanel != null) {
            mainPanel.remove(buildingInfoPanel);
        }

        buildingInfoPanel = new BuildingInfoPanel(buildingName, buildingLevel, energyCost, workerCost, yield, game);
        mainPanel.add(buildingInfoPanel);
        mainPanel.repaint();
    }

}

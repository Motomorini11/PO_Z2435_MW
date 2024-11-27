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
    private InfoPanel infoPanel;
    private Building currentBuilding;
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
    private AttackDisplay attackDisplay;

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

        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (infoPanel.getBounds().contains(e.getPoint())) {
                    return;
                }
                closeInfoPanel();
            }
        });

        infoPanel = new InfoPanel();
        infoPanel.setBounds(55, 450, 300, 400);
        add(infoPanel);

        setupTurnCounter(mainPanel, 80, 50, 60, "Arial");
        attackDisplay = new AttackDisplay(game);
        attackDisplay.setBounds(58, 280, 300, 90);
        mainPanel.add(attackDisplay);

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

        Building Tartak = new Building(1, 1, 20, 1, 10, 0, 0,1);
        RectangleSpawner.spawnRectangle(mainPanel, 1000, 670, 200, 100, 0, 150, 0, 200, "Sawmill", "Arial", 20, Tartak, infoPanel);

        Building Kamien = new Building(2, 2, 20, 1, 0, 1, 1,2);
        RectangleSpawner.spawnRectangle(mainPanel, 500, 270, 200, 100, 169, 169, 169, 220, "Quarry", "Arial", 20, Kamien, infoPanel);

        Building Farm = new Building(3, 2, 20, 1, 0, 1, 1,3);
        RectangleSpawner.spawnRectangle(mainPanel,1140,255,200,100,155,118,83,220,"Farm","Arial", 20, Farm, infoPanel);

        // Return button setup
        JButton returnButton = new JButton("Back");
        returnButton.setFont(new Font("Arial", Font.BOLD, 18));
        returnButton.setBounds(40, 1010, 120, 40);
        returnButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, "Exit? Only your current highest turn will be saved!", "Didn't bother with saving hi hi", JOptionPane.YES_NO_OPTION);
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
            attackDisplay.handleTurnChange();
            updateAllResources();
            updateTurnCounter();
            workersDisplay.updateCounts(game.getWorkers(), game.getMaxWorkers(),game.getFreeWorkers());
            if (game.checkForGameOver()) {
                JOptionPane.showMessageDialog(this, "Game Over! All workers are dead. You survived " + game.getTurn() + " turns.");
                game.storeHighestTurn();
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
        turnCounterLabel = new JLabel("Turn: " + game.getTurn());
        turnCounterLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
        turnCounterLabel.setForeground(Color.WHITE);
        turnCounterLabel.setBounds(x, y, 250, 100);

        // Create a semi-transparent background color
        Color semiTransparentBackground = new Color(0, 0, 0, 128);

        turnCounterLabel.setOpaque(true);
        turnCounterLabel.setBackground(semiTransparentBackground);
        mainPanel.add(turnCounterLabel);
    }

    private void updateTurnCounter() {
        turnCounterLabel.removeAll();
        turnCounterLabel.setText("Turn: " + game.getTurn());
        repaint();
    }
    private void closeInfoPanel() {
        infoPanel.setVisible(false);
        currentBuilding = null;
    }
}
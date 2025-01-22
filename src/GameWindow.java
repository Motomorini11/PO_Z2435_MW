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
                    updateAllResources();
                    return;
                }
                closeInfoPanel();
                updateAllResources();
            }
        });

        infoPanel = new InfoPanel(game);
        infoPanel.setBounds(55, 450, 300, 400);
        add(infoPanel);

        setupTurnCounter(mainPanel, 80, 50, 60, "Arial");
        attackDisplay = new AttackDisplay(game);
        attackDisplay.setBounds(58, 280, 300, 90);
        mainPanel.add(attackDisplay);

        energyDisplay = new ResourceDisplay(new ImageIcon("images/energy.png"), game.getEnergy(), 75, 75,false);
        energyDisplay.setBounds(1715, 290, 150, 75);
        mainPanel.add(energyDisplay);

        woodDisplay = new ResourceDisplay(new ImageIcon("images/wood.png"), game.getWood(), 75, 75,false);
        woodDisplay.setBounds(1545, 500, 150, 75);
        mainPanel.add(woodDisplay);

        stoneDisplay = new ResourceDisplay(new ImageIcon("images/stone.png"), game.getStone(), 75, 75,false);
        stoneDisplay.setBounds(1545, 710, 150, 75);
        mainPanel.add(stoneDisplay);

        foodDisplay = new ResourceDisplay(new ImageIcon("images/food.png"), game.getFood(), 75, 75,false);
        foodDisplay.setBounds(1715, 500, 150, 75);
        mainPanel.add(foodDisplay);

        goldDisplay = new ResourceDisplay(new ImageIcon("images/gold.png"), game.getGold(), 75, 75,false);
        goldDisplay.setBounds(1545, 290, 150, 75);
        mainPanel.add(goldDisplay);

        workersDisplay = new ResourceDisplay(new ImageIcon("images/man.png"), game.getWorkers(), 75, 75,true);
        workersDisplay.updateCounts(game.getWorkers(), game.getMaxWorkers(),game.getFreeWorkers());
        workersDisplay.setBounds(1590, 70, 220, 75);
        mainPanel.add(workersDisplay);

        swordDisplay = new ResourceDisplay(new ImageIcon("images/sword.png"), game.getSword(), 75, 75,false);
        swordDisplay.setBounds(1715, 710, 150, 75);
        mainPanel.add(swordDisplay);

        Building Sawmill = new Building(3, 1, 10, 2, 20, 10,1);
        RectangleSpawner.spawnRectangle(mainPanel, 1100, 670, 200, 100, 0, 150, 0, 200, "Sawmill", "Arial", 20, Sawmill, infoPanel);

        Building Quarry = new Building(3, 2, 5, 2, 10, 20,1);
        RectangleSpawner.spawnRectangle(mainPanel, 700, 150, 200, 100, 169, 169, 169, 220, "Quarry", "Arial", 20, Quarry, infoPanel);

        Building Farm = new Building(2, 4, 20, 5, 5, 5,1);
        RectangleSpawner.spawnRectangle(mainPanel,1180,260,200,100,155,118,83,220,"Farm","Arial", 20, Farm, infoPanel);

        Building House = new Building(5, 2, 1, 10, 10, 10,1);
        RectangleSpawner.spawnRectangle(mainPanel,450,400,200,100,161,102,47,220,"House","Arial", 20, House, infoPanel);

        Building Armory = new Building(4, 3, 3, 10, 15, 15,1);
        RectangleSpawner.spawnRectangle(mainPanel,620,750,200,100,247,133,177,220,"Armory","Arial", 20, Armory, infoPanel);

        Building Castle = new Building(0, 0, 4, 50, 50, 50,1);
        RectangleSpawner.spawnRectangle(mainPanel,860,400,200,100,103,103,103,220,"Castle","Arial", 20, Castle, infoPanel);




        // Return button setup
        JButton returnButton = new JButton("Back");
        returnButton.setFont(new Font("Arial", Font.BOLD, 25));
        returnButton.setBounds(125, 940, 150, 75);
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
        endTurnButton.setBounds(1630, 920, 150, 75);
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

        TutorialDialog tutorialDialog = new TutorialDialog(this);
        tutorialDialog.setVisible(true);
    }

    public void updateAllResources() {
        energyDisplay.updateCount(game.getEnergy());
        woodDisplay.updateCount(game.getWood());
        stoneDisplay.updateCount(game.getStone());
        foodDisplay.updateCount(game.getFood());
        goldDisplay.updateCount(game.getGold());
        workersDisplay.updateCounts(game.getWorkers(), game.getMaxWorkers(),game.getFreeWorkers());
        swordDisplay.updateCount(game.getSword());

    }

    private void setupTurnCounter(JPanel mainPanel, int x, int y, int fontSize, String fontName) {
        turnCounterLabel = new JLabel("Turn: " + game.getTurn());
        turnCounterLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
        turnCounterLabel.setForeground(Color.WHITE);
        turnCounterLabel.setBounds(x, y, 250, 100);

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
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    private Game game;

    public MainMenu() {
        setTitle("Castle Craft");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Background image
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("images/MMback.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Center panel for buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        JButton newGameButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");

        Font buttonFont = new Font("Arial", Font.BOLD, 30);
        newGameButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createVerticalStrut(200));
        centerPanel.add(newGameButton);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(exitButton);
        centerPanel.add(Box.createVerticalStrut(100));

        // High Score label
        game = new Game();
        int record = game.getHighestTurn();
        if (record > 0) {
            JLabel recordLabel = new JLabel("High Score: " + record);
            recordLabel.setFont(new Font("Arial", Font.BOLD, 18));
            recordLabel.setForeground(Color.BLACK);
            recordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            centerPanel.add(recordLabel);
        }

        // Footer panel for credits
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setOpaque(false);
        JLabel creditsLabel = new JLabel("By: Marek Wiater & GPT");
        creditsLabel.setForeground(Color.WHITE);
        footerPanel.add(creditsLabel);


        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
        backgroundPanel.add(footerPanel, BorderLayout.SOUTH);


        add(backgroundPanel);


        newGameButton.addActionListener(e -> {
            dispose();
            new GameWindow();
        });

        exitButton.addActionListener(e -> System.exit(0));
    }
}

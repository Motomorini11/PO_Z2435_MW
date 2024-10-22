// MainMenu.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Castle Craft");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.BLACK);

        JButton newGameButton = new JButton("Nowa Gra");
        JButton exitButton = new JButton("Wyjście");

        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        newGameButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createVerticalStrut(200));
        centerPanel.add(newGameButton);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(exitButton);
        centerPanel.add(Box.createVerticalStrut(100));

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(Color.DARK_GRAY);
        JLabel creditsLabel = new JLabel("By: Marek Wiater & GPT");
        creditsLabel.setForeground(Color.WHITE);
        footerPanel.add(creditsLabel);

        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();

                new GameWindow();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(footerPanel, BorderLayout.SOUTH);
        add(panel);
    }
}

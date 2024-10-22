import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Castle Craft - Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setSize(1920, 1080);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setPreferredSize(new Dimension(1920, 1080));

        PlayableAreaPanel playableArea = new PlayableAreaPanel();
        playableArea.setBounds(420, 0, 1080, 1080);
        mainPanel.add(playableArea);


        JButton returnButton = new JButton("Powrót");
        returnButton.setFont(new Font("Arial", Font.BOLD, 18));
        returnButton.setBounds(20, 20, 120, 40);
        mainPanel.add(returnButton);


        returnButton.addActionListener(e -> {

            int result = JOptionPane.showConfirmDialog(this, "Wyjść?", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {

                dispose();
                new MainMenu().setVisible(true);
            }
        });


        add(mainPanel);

        setVisible(true);
    }


    class PlayableAreaPanel extends JPanel {
        private BufferedImage backgroundImage;

        public PlayableAreaPanel() {
            try {

                backgroundImage = ImageIO.read(new File("images/BCGR.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            setPreferredSize(new Dimension(1080, 1080));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {

                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}

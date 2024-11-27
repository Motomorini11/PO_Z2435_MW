import javax.swing.*;
import java.awt.*;

public class AttackDisplay extends JComponent {
    private JLabel countdownLabel;
    private JLabel powerLabel;
    private Game game;
    private int nextAttackTurn;
    private int attackPower;

    public AttackDisplay(Game game) {
        this.game = game;
        this.nextAttackTurn = calculateNextAttackTurn(game.getTurn());
        this.attackPower = calculateAttackPower(game.getTurn());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        countdownLabel = new JLabel();
        powerLabel = new JLabel();

        Font displayFont = new Font("Arial", Font.BOLD, 30);
        countdownLabel.setFont(displayFont);
        powerLabel.setFont(displayFont);

        countdownLabel.setForeground(Color.WHITE);
        powerLabel.setForeground(Color.WHITE);

        add(countdownLabel);
        add(powerLabel);

        updateDisplay();
    }

    public void handleTurnChange() {
        int currentTurn = game.getTurn();

        if (nextAttackTurn == 1) {
            if (game.getSword() >= attackPower) {
                game.useSword(attackPower);
                nextAttackTurn = calculateNextAttackTurn(currentTurn);
                attackPower = calculateAttackPower(currentTurn);
            } else {
                SwingUtilities.getWindowAncestor(this);
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Game Over! Your castle has been raided.");
                game.storeHighestTurn();
                System.exit(0);
            }
        }
        updateDisplay();
    }

    private void updateDisplay() {
        int turnsLeft = --nextAttackTurn;
        countdownLabel.setText("Attack in: " + turnsLeft + " turns");
        powerLabel.setText("Power: " + attackPower);
        repaint();
    }

    private int calculateNextAttackTurn(int currentTurn) {
        double value = 15 / (1 + 0.1 * currentTurn) + 5;
        return (int) Math.ceil(value);
    }

    private int calculateAttackPower(int currentTurn) {
        return (int) (20 + currentTurn * 1.5);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 0, 0, 128));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2d.dispose();
        super.paintComponent(g);
    }
}

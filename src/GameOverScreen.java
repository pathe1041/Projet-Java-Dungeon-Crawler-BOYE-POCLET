import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

public class GameOverScreen extends JPanel {

    private final JFrame parentFrame;
    private Image gameOverImage;
    private boolean hasSwitched = false; // ✅ Protection pour éviter double exécution

    public GameOverScreen(JFrame frame) {
        this.parentFrame = frame;
        setLayout(null);

        // Charger l'image Game Over
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("img/GameOver.png")) {
            if (is != null) {
                gameOverImage = ImageIO.read(is);
            } else {
                System.err.println("❌ Image Game Over introuvable !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToTitleScreen() {
        SwingUtilities.invokeLater(() -> {
            parentFrame.getContentPane().removeAll();
            TitleScreen titleScreen = new TitleScreen(parentFrame);
            parentFrame.getContentPane().add(titleScreen);
            parentFrame.revalidate();
            parentFrame.repaint();
            titleScreen.requestFocusInWindow(); // ✅ Transfert de focus
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameOverImage != null) {
            g.drawImage(gameOverImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", getWidth() / 2 - 120, getHeight() / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Appuyez sur Entrée pour revenir au menu", getWidth() / 2 - 150, getHeight() / 2 + 40);
        }
    }
}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class TitleScreen extends JPanel implements KeyListener {
    private Image titleImage;
    private JFrame frame;

    public TitleScreen(JFrame frame) {
        this.frame = frame;

        try {
            titleImage = ImageIO.read(new File("C:/Users/pathe/IdeaProjects/PROJET_JAVA/src/img/titre.png")); // ✅ Charge l’image de l’écran titre
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("⚠️ Erreur : Impossible de charger l'image de l'écran titre !");
        }

        frame.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (titleImage != null) {
            g.drawImage(titleImage, 0, 0, getWidth(), getHeight(), this);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.revalidate();

            try {
                new Main(frame);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

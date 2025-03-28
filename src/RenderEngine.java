import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.IOException;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;
    private BarreDeVie barreDeVie;
    //private Image gameOverImage;
    private boolean isGameOver = false;
    private Runnable onGameOver;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
        barreDeVie = new BarreDeVie(100, 10, 10, 200, 20);
        //try {
        //    gameOverImage = ImageIO.read(new File("C:/Users/pathe/IdeaProjects/PROJET_JAVA/src/img/GameOver.png")); // ✅ Charge l'image Game Over
        //} catch (IOException e) {
         //   e.printStackTrace();
         //   System.out.println(" Erreur : Impossible de charger l'image Game Over !");
        //}

    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }
    public void updateHealth(int damage) {
        barreDeVie.subirDegats(damage);
        repaint();
        if (barreDeVie.getVieActuelle() <= 0 && onGameOver != null) {
            onGameOver.run();
        }
    }
    public void showGameOver() {
        isGameOver = true;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList) {
            renderObject.draw(g);
        }
        barreDeVie.dessiner(g);

        //if (isGameOver && gameOverImage != null) {
        //    g.drawImage(gameOverImage, 0, 0, 400, 600, null);
        //}
    }
    public void setOnGameOver(Runnable onGameOver) {
        this.onGameOver = onGameOver;
    }

    @Override
    public void update(){
        this.repaint();
    }
}
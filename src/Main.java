import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    ArrayList<Trap> traps;

    public Main(JFrame frame) throws Exception {
        this.displayZoneFrame = frame;
        displayZoneFrame.getContentPane().removeAll();
        displayZoneFrame.setSize(655, 675);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Playground level = new Playground("src/data/level1.txt");
        double startX = level.getStartX();  // ✅ Récupération des coordonnées de départ
        double startY = level.getStartY();

        DynamicSprite hero = new DynamicSprite(startX, startY,
                ImageIO.read(new File("src/img/heroTileSheetLowRes.png")), 48, 50);

        renderEngine = new RenderEngine(displayZoneFrame);

        renderEngine.setOnGameOver(() -> {
            frame.getContentPane().removeAll();
            GameOverScreen gos = new GameOverScreen(frame);
            frame.getContentPane().add(gos);
            frame.revalidate();
            frame.repaint();

            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(new TitleScreen(frame));
                        frame.revalidate();
                        frame.repaint();
                        frame.removeKeyListener(this); // ✅ Important pour éviter les doublons
                    }
                }
            });
        });

        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        traps = level.getTrapList();
        if (traps == null) traps = new ArrayList<>();

        // Timers
        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> {
            physicEngine.update();
            if (physicEngine.isOnTrap(hero, traps)) {
                renderEngine.updateHealth(1);
            }
        });

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }





    public static void main(String[] args) {
        JFrame frame = new JFrame("Dungeon Crawler");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);

        TitleScreen titleScreen = new TitleScreen(frame);
        frame.getContentPane().add(titleScreen);
        frame.setVisible(true);
    }

}
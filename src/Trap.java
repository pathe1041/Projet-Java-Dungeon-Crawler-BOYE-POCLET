import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Trap extends Sprite {
    public Trap(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x, y, width, height);
    }
}

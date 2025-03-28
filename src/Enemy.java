import java.awt.Image;
import java.util.ArrayList;

public class Enemy extends DynamicSprite {
    private int squareStep = 0;
    private int squareMaxSteps = 50;
    private Direction direction = Direction.EAST;

    public Enemy(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    public void update(ArrayList<SolidSprite> environment) {
        moveInSquare();


        if (!isMovingPossible(new ArrayList<>(environment))) {
            turnRight();
        }
    }

    private void moveInSquare() {

        switch (direction) {
            case EAST -> this.x += speed;
            case WEST -> this.x -= speed;
            case NORTH -> this.y -= speed;
            case SOUTH -> this.y += speed;
        }


        squareStep++;


        if (squareStep >= squareMaxSteps) {
            squareStep = 0;
            turnRight();
        }
    }

    private void turnRight() {
        switch (direction) {
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
            case NORTH -> direction = Direction.EAST;
        }
    }
}

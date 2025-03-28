import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();
    private double startX;
    private double startY;

    public Playground (String pathName){
        try{
            final Image imageTree = ImageIO.read(new File("src/img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("src/img/grass.png"));
            final Image imageRock = ImageIO.read(new File("src/img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("src/img/trap.png"));

            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            final int imageTrapWidth = imageTrap.getWidth(null);
            final int imageTrapHeight = imageTrap.getHeight(null);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line!= null){
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'T' : environment.add(new SolidSprite(columnNumber*imageTreeWidth,
                                lineNumber*imageTreeHeight,imageTree, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ' : environment.add(new Sprite(columnNumber*imageGrassWidth,
                                lineNumber*imageGrassHeight, imageGrass, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R' : environment.add(new SolidSprite(columnNumber*imageRockWidth,
                                lineNumber*imageRockHeight, imageRock, imageRockWidth, imageRockHeight));
                            break;
                        case 'X':  environment.add(new Trap(columnNumber * imageTrapWidth,
                                    lineNumber * imageTrapHeight,
                                    imageTrap, imageTrapWidth, imageTrapHeight));
                            break;
                        case 'H':
                            // On met une tuile de sol visuelle
                            environment.add(new Sprite(
                                    columnNumber * imageGrassWidth,
                                    lineNumber * imageGrassHeight,
                                    imageGrass,
                                    imageGrassWidth,
                                    imageGrassHeight
                            ));

                            // On place aussi les coordonnées du héros
                            startX = columnNumber * imageGrassWidth;
                            startY = lineNumber * imageGrassHeight;
                            break;
                    }
                    columnNumber++;
                }
                columnNumber =0;
                lineNumber++;
                line=bufferedReader.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Sprite> getSolidSpriteList(){
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Trap> getTrapList() {
        ArrayList<Trap> trapList = new ArrayList<>();
        for (Sprite sprite : environment) {
            if (sprite instanceof Trap) trapList.add((Trap) sprite);
        }
        return trapList;
    }


    public ArrayList<Displayable> getSpriteList(){
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }
    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

}
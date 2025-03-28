import java.lang.reflect.Array;
import java.util.ArrayList;

public class PhysicEngine implements Engine{
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList <Sprite> environment;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    public void addToEnvironmentList(Sprite sprite){
        if (!environment.contains(sprite)){
            environment.add(sprite);
        }
    }
    public boolean isOnTrap(DynamicSprite hero, ArrayList<Trap> traps) {
        for (Trap trap : traps) {
            if (hero.getHitBox().intersects(trap.getHitBox())) {
                return true;
            }
        }
        return false;
    }


    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment=environment;
    }

    public void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)){
            movingSpriteList.add(sprite);
        }
    }
    public void addEnemy(Enemy enemy) {
        if (!enemies.contains(enemy)) {
            enemies.add(enemy);
            movingSpriteList.add(enemy);
        }
    }

    @Override
    public void update() {
        for(DynamicSprite dynamicSprite : movingSpriteList){
            dynamicSprite.moveIfPossible(environment);

        }
    }

}